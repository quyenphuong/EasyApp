package com.example.easyapp.feature;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.easyapp.R;
import com.example.easyapp.api.CustomerMapActivity;
import com.example.easyapp.model.UserModel;
import com.example.easyapp.ui.ForgotActivity;
import com.example.easyapp.ui.HomeActivity;
import com.example.easyapp.ui.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ShippingActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    EditText addressHome, addressShip;
    Button price, callbackship;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        getAddress();

        //////////
        addressHome = findViewById(R.id.address_home);
        addressShip = findViewById(R.id.address_ship);
        price = findViewById(R.id.price_temp_button);
        callbackship = findViewById(R.id.call_ship);
        /////////

        callbackship.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();

//                Intent intent= new Intent(ShippingActivity.this, HomeActivity.class);
//                startActivity(intent);
            }
        });

        price.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPriceDialog(Gravity.CENTER);
            }
        });



    }
    private void getAddress() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();

            mDatabase.child("User").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Lấy dữ liệu thất bại", task.getException());
                    }
                    else {
                        UserModel um = task.getResult().getValue(UserModel.class);

                        addressHome.setText(um.getAddress());
                    }
                }
            });
        }
    }




    private void openPriceDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        final Dialog dialog1 = new Dialog(this);
        final Dialog dialog2 = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.price_temp);
        dialog1.setContentView(R.layout.layout_dialog_time);
        dialog2.setContentView(R.layout.layout_dialog_cod);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        /////// click ra tắt dialog hoặc không
        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        /////////---------------------------\
        Window windows = dialog.getWindow();
        if(windows == null){
            return;
        }
        windows.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        windows.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowsAttributes = window.getAttributes();
        windowsAttributes.gravity = gravity;
        windows.setAttributes(windowAttributes);

        /////// click ra tắt dialog hoặc không
        if(Gravity.CENTER == gravity){
            dialog1.setCancelable(true);
        }else{
            dialog1.setCancelable(false);
        }



        TextView callPrice, callNextCodDialog, totalMoney, callBackTime;
        EditText addressShipPrice, addressHomePrice, moneyInput;
        LinearLayout calltime;
        Button billDetailButton, pickUpButton, bookButton, buttonAddMoney;

        /////////////////////////////
        callBackTime = dialog1.findViewById(R.id.call_back_time);
        callPrice = dialog.findViewById(R.id.call_price);
        callNextCodDialog = dialog.findViewById(R.id.call_next_cod_dialog);
        addressShipPrice = dialog.findViewById(R.id.address_ship_price);
        addressHomePrice = dialog.findViewById(R.id.address_home_price);
        calltime = dialog.findViewById(R.id.call_next_dialog_price);
        totalMoney = dialog.findViewById(R.id.money);
        billDetailButton = dialog.findViewById(R.id.bill_detail_button);
        pickUpButton = dialog1.findViewById(R.id.pickup);
        bookButton = dialog1.findViewById(R.id.book);
        buttonAddMoney = dialog2.findViewById(R.id.add_cod_button);
        moneyInput = dialog2.findViewById(R.id.money_input);

        String s1 = addressHome.getText().toString();
        String s2 = addressShip.getText().toString();
        addressHomePrice.setText(s1);
        addressShipPrice.setText(s2);
        ////

        //////// call back
        calltime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                dialog1.setContentView(R.layout.layout_dialog_time);
                dialog1.show();
            }
        });
        //////// gọi vào COD
        callNextCodDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                dialog.setContentView(R.layout.layout_dialog_cod);
                dialog2.show();
            }
        });
        ///////////
        buttonAddMoney.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        //////// gọi qua chi tiết Bill. Activity khác

        billDetailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                dialog.dismiss();
                Intent intent= new Intent(getApplicationContext(), BillActivity.class );
                startActivity(intent);
            }
        });

        /////// call back
        callPrice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        ///
//        callBackTime.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                dialog.setContentView(R.layout.price_temp);
//            }
//        });
        // nhán vô lấy hàng ngay quay lại
        pickUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog1.dismiss();
//                dialog.show();

            }
        });


        //////////////
        /////////////
        ////////////
        /////////////
//
//        callNextCodDialog.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                dialog2.;
//
//            }
//        });



//        dialog1.show();
//        dialog2.show();
        dialog.show();
    }
}
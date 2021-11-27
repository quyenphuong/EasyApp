package com.example.easyapp.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.example.easyapp.ui.ForgotActivity;
import com.example.easyapp.ui.HomeActivity;
import com.example.easyapp.ui.LoginActivity;

import java.util.List;

public class ShippingActivity extends AppCompatActivity {


    EditText addressHome, addressShip;
    Button price, callbackship;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        //////////
        addressHome = findViewById(R.id.address_home);
        addressShip = findViewById(R.id.address_ship);
        price = findViewById(R.id.price_temp);
        callbackship = findViewById(R.id.call_ship);
        /////////

        callbackship.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent= new Intent(ShippingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        price.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPriceDialog(Gravity.CENTER);
            }
        });

    }

    private void openPriceDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.price_temp);

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

        TextView callPrice, callNextCodDialog;
        EditText addressShip, addressHome;
        LinearLayout callnext;
        RelativeLayout togetherMoney;
        Button billDetailButton;
        /////////////////////////////
        callPrice = dialog.findViewById(R.id.call_price);
        callNextCodDialog = dialog.findViewById(R.id.call_next_cod_dialog);
        addressShip = dialog.findViewById(R.id.address_ship);
        addressHome = dialog.findViewById(R.id.address_home);
        callnext = dialog.findViewById(R.id.call_next_dialog_price);
        togetherMoney = dialog.findViewById(R.id.together_money);
        billDetailButton = dialog.findViewById(R.id.bill_detail_button);


        /////////
        callPrice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              dialog.dismiss();
            }
        });

        callNextCodDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.setContentView(R.layout.price_temp);

            }
        });




        dialog.show();
    }
}
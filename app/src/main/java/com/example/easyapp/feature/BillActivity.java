package com.example.easyapp.feature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.easyapp.R;
import com.example.easyapp.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class BillActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference zDatabase = FirebaseDatabase.getInstance().getReference();
    TextView callBackBill;
    EditText nameBillCustomer, phoneBillCustomer, addressBillCustomer, nameBillShip, phoneBillShip, addressBillShip, noteBillShip;
    Spinner spinnerType, spinnerKg;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        getInforCustomer();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        /////////////////////////////////////////////////




        ////////
        nameBillShip = findViewById(R.id.name_bill_ship);
        phoneBillShip = findViewById(R.id.phone_bill_ship);
        addressBillShip = findViewById(R.id.address_bill_ship);
        noteBillShip = findViewById(R.id.note_bill_ship);
        nameBillCustomer = findViewById(R.id.name_bill_customer);
        phoneBillCustomer = findViewById(R.id.phone_bill_customer);
        addressBillCustomer = findViewById(R.id.address_bill_customer);
        spinnerType = findViewById(R.id.spinner_type);
        spinnerKg = findViewById(R.id.spinner_kg);
        nextButton = findViewById(R.id.next_button);
        callBackBill = findViewById(R.id.call_bill);
        //////////

        String[] itemsType = new String[]{"Tài liệu","Thực phẩm","Hàng dễ vỡ","Hàng hóa thương mại"};
        ArrayAdapter<String> adapterType = new ArrayAdapter<>(spinnerType.getContext(), android.R.layout.simple_spinner_dropdown_item, itemsType);
        spinnerType.setAdapter(adapterType);
        ///////
        String[] itemsKg = new String[]{"1Kg","2kg","3kg","4kg","5kg","10kg","Trên 10kg"};
        ArrayAdapter<String> adapterKg = new ArrayAdapter<>(spinnerKg.getContext(), android.R.layout.simple_spinner_dropdown_item, itemsKg);
        spinnerKg.setAdapter(adapterKg);


        /////
        callBackBill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                dialog.setContentView(R.layout.price_temp);
            }
        });
        //////////
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BillActivity.this, ReBillActivity.class);
                startActivity(intent);
            }
        });


    }
    private void getInforCustomer() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();

            zDatabase.child("User").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Lấy dữ liệu thất bại", task.getException());
                    }
                    else {
                        UserModel um = task.getResult().getValue(UserModel.class);
                        nameBillCustomer.setText(um.getName());
                        phoneBillCustomer.setText(um.getPhone());
                        addressBillCustomer.setText(um.getAddress());
                    }
                }
            });
        }
    }
    ///////////////



}
package com.example.easyapp.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easyapp.R;

import java.util.List;

public class ShippingActivity extends AppCompatActivity {


    EditText addressHome, addressShip;
    Button price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        //////////
        addressHome = findViewById(R.id.address_home);
        addressShip = findViewById(R.id.address_ship);
        price = findViewById(R.id.price_temp);

        /////////



    }
}
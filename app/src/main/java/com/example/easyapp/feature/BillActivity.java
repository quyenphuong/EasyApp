package com.example.easyapp.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.easyapp.R;

import org.w3c.dom.Text;

public class BillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        TextView callBackBill;

        ////////
        callBackBill = findViewById(R.id.call_bill);
        /////
        callBackBill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(BillActivity.this, ShippingActivity.class);
                startActivity(intent);
            }
        });




    }
}
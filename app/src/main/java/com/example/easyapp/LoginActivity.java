package com.example.easyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    ImageView image;
    TextView signinText, callSignUp;
    TextInputLayout username, password;
    Button go_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callSignUp=findViewById(R.id.signup_screen);

        image=findViewById(R.id.logoimg);
        signinText=findViewById(R.id.signintext);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        callSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                show();
            }
        });
    }

    public void show(){
        Intent intent= new Intent(LoginActivity.this, SignUpActivity.class);
        Pair[] pairs=new Pair[7];
        pairs[0]=new Pair<View, String>(image, "logo_image");
        pairs[4]=new Pair<View, String>(username, "username_tran");
        pairs[5]=new Pair<View, String>(password, "password_tran");
        pairs[6]=new Pair<View, String>(callSignUp, "login_signup_tran");
//            ActivityOptions options=makeSceneTransitionAnimation(Login_Activity.this, pairs);
//            startActivity(intent, options.toBundle());
        startActivity(intent);
    }
}
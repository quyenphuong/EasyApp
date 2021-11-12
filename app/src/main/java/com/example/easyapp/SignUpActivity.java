package com.example.easyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class SignUpActivity extends AppCompatActivity {

    //Variables
    private TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    private Button btnRegister, btnExit;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        regName = (TextInputLayout) findViewById(R.id.name);
        regUsername = (TextInputLayout) findViewById(R.id.email);
        regEmail = (TextInputLayout) findViewById(R.id.username);
        regPhoneNo = (TextInputLayout) findViewById(R.id.phoneNo);
        regPassword = (TextInputLayout) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.sign_next_btn);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKi();

            }
        });
        btnExit = (Button) findViewById(R.id.btnLogin);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void DangKi(){

        String email = regEmail.getEditText().getText().toString();

        String password = regPassword.getEditText().getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           Toast.makeText(SignUpActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this,"Đăng kí thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
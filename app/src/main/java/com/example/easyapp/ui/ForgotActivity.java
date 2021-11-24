package com.example.easyapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.easyapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {

    private EditText emailEd;
    private Button resetPassword;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        emailEd = findViewById(R.id.email);
        resetPassword = findViewById(R.id.btnResetPassword);

        auth = FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailEd.getText().toString().trim();

        if(email.isEmpty()){
            emailEd.setError("Email không được để trống!");
            emailEd.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEd.setError("Vui lòng nhập đúng email!");
            emailEd.requestFocus();
            return;
        }


        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotActivity.this,"Kiểm tra email của bạn để thiết lập lại mật khẩu của bạn",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(ForgotActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ForgotActivity.this,"Thử lại! Có điều gì đó không ổn đã xảy ra!",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
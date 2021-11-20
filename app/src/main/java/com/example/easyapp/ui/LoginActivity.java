package com.example.easyapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easyapp.R;
import com.example.easyapp.ui.HomeActivity;
import com.example.easyapp.ui.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ImageView image;
    TextView signinText, callSignUp, forgetSign;
    TextInputLayout edtemail, edtpassword;
    Button goHome;
    CheckBox rememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.signup_screen);
        goHome = findViewById(R.id.gobtn);
        image = findViewById(R.id.logoimg);
        forgetSign = findViewById(R.id.forgotpass);
        rememberMe = findViewById(R.id.remember);
        signinText = findViewById(R.id.signintext);
        edtemail = findViewById(R.id.email);
        edtpassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        goHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(edtemail.getEditText().getText().toString().length()>0 && edtpassword.getEditText().getText().toString().length()>0){
                   DangNhap();
                }
                else{
                    edtemail.setError("Bạn chưa nhập Email");
                    edtpassword.setError("Bạn chưa nhập mật khẩu");
                }

            }
        });
        callSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                show();
            }
        });
    }
    private boolean Check(){
        return true;
    }

    public void show(){
        Intent intent= new Intent(LoginActivity.this, SignUpActivity.class);
        Pair[] pairs=new Pair[7];
        pairs[0]=new Pair<View, String>(image, "logo_image");
        pairs[1]=new Pair<View, String>(signinText, "signintext");

        pairs[2]=new Pair<View, String>(edtemail, "username_tran");
        pairs[3]=new Pair<View, String>(edtpassword, "password_tran");

        pairs[6]=new Pair<View, String>(callSignUp, "login_signup_tran");
//            ActivityOptions options=makeSceneTransitionAnimation(Login_Activity.this, pairs);
//            startActivity(intent, options.toBundle());
        startActivity(intent);
    }

    private void DangNhap(){
        String email = edtemail.getEditText().getText().toString();
        String password = edtpassword.getEditText().getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
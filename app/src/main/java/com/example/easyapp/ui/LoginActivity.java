package com.example.easyapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ImageView image;
    TextView callSignUp, forgetSign;
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

        edtemail = findViewById(R.id.email);
        edtpassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

        forgetSign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });


        goHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               DangNhap();

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


        pairs[1]=new Pair<View, String>(edtemail, "username_tran");
        pairs[2]=new Pair<View, String>(edtpassword, "password_tran");

        pairs[3]=new Pair<View, String>(callSignUp, "login_signup_tran");
//            ActivityOptions options=makeSceneTransitionAnimation(Login_Activity.this, pairs);
//            startActivity(intent, options.toBundle());
        startActivity(intent);
    }

    private void DangNhap(){
        String email = edtemail.getEditText().getText().toString();
        String password = edtpassword.getEditText().getText().toString();
        if(email.isEmpty()){
            edtemail.setError("Email không được để trống!");
            edtemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtemail.setError("Vui lòng nhập đúng email!");
            edtemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edtpassword.setError("Mật khẩu không được để trống!");
            edtpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            edtpassword.setError("Mật khẩu phải lớn hơn 6 kí tự!");
            edtpassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if(user.isEmailVerified()){
                                Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();

                            }else{
                                user.sendEmailVerification();
                                Toast.makeText(LoginActivity.this,"Vui lòng kiểm tra email",Toast.LENGTH_LONG).show();

                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
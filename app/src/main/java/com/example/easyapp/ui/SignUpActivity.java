package com.example.easyapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.easyapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {

    //Variables
    private TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword, regConfirmPassword;
    private Button btnRegister, btnExit;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        regName = (TextInputLayout) findViewById(R.id.name);
        regUsername = (TextInputLayout) findViewById(R.id.username);
        regEmail = (TextInputLayout) findViewById(R.id.email);
        regPhoneNo = (TextInputLayout) findViewById(R.id.phoneNo);
        regPassword = (TextInputLayout) findViewById(R.id.password);
        regConfirmPassword = (TextInputLayout) findViewById(R.id.confirm_password);
        btnRegister = (Button) findViewById(R.id.sign_next_btn);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(regName.getEditText().getText().toString().length()>0 && regUsername.getEditText().getText().toString().length()>0 && regEmail.getEditText().getText().toString().length()>0 && regPhoneNo.getEditText().getText().toString().length()>0 && regPassword.getEditText().getText().toString().length()>0){
                    if(Check()){
                        if(regPassword.getEditText().getText().toString().trim().compareTo(regConfirmPassword.getEditText().getText().toString())==0){
                            DangKi();
                            GetInfo(regName.getEditText().getText().toString(),regUsername.getEditText().getText().toString(),regEmail.getEditText().getText().toString(),regPhoneNo.getEditText().getText().toString());
                        }else{
                            regPassword.setError("Mật khẩu không khớp");
                        }
                    }
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Bạn chưa nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }

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
    private boolean Check(){
        final TextInputLayout nameValidate = (TextInputLayout) findViewById(R.id.name);
        final TextInputLayout usernameValidate = (TextInputLayout) findViewById(R.id.username);
        final TextInputLayout emailValidate = (TextInputLayout) findViewById(R.id.email);
        final TextInputLayout phoneValidate = (TextInputLayout) findViewById(R.id.phoneNo);
        final TextInputLayout passwordValidate = (TextInputLayout) findViewById(R.id.password);
        final TextInputLayout confirmpassValidate = (TextInputLayout) findViewById(R.id.confirm_password);

        String namez = nameValidate.getEditText().getText().toString().trim();
        String emailz = emailValidate.getEditText().getText().toString().trim();
        String userz = usernameValidate.getEditText().getText().toString().trim();
        String passz = passwordValidate.getEditText().getText().toString().trim();
        String phonez = phoneValidate.getEditText().getText().toString().trim();
        String confirmpassz = confirmpassValidate.getEditText().getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String userPattern = "^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$";
        String namePattern = "^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$";
        String passPattern =  "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        String phonePattern = "[0-9]";
        String confirmpassPattern ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        if(!namez.matches(namePattern)){
            regName.setError("Tên người dùng không hợp lệ");
            return false;
        }
        if(!emailz.matches(emailPattern)){
            regEmail.setError("Email bạn nhập không hợp lệ !");
            return false;
        }
        if(!userz.matches(userPattern)){
            regUsername.setError("UserName bạn nhập không hợp lệ !");
            return false;
        }
        if(!passz.matches(passPattern)){
            regPassword.setError("Mật khẩu phải chứa đủ (kí tự hoa, thường, số và kí tự đặc biệt) !");
            return false;
        }
        if(!phonez.matches(phonePattern) && phonez.length() != 10){
            regPhoneNo.setError("Số điện thoại không chứ (chữ, kí tự và 10 số) !");
            return false;
        }
        if(!confirmpassz.matches(confirmpassPattern)){
            regConfirmPassword.setError("Mật khẩu phải chứa đủ (kí tự hoa, thường, số và kí tự đặc biệt) !");
            return false;
        }
        return true;
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
                            //Toast.makeText(SignUpActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                            //Intent intent= new Intent(SignUpActivity.this, LoginActivity.class);
                            //startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Toast.makeText(SignUpActivity.this,"Đăng kí thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void GetInfo(String Name,String UserName, String Email, String Phone) {

        Map<String,String> data = new HashMap<>();
        data.put("Name:",Name);
        data.put("UserName:",UserName);
        data.put("Email:",Email);
        data.put("Phone:",Phone);
        db.collection("Users").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Toast.makeText(SignUpActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this,"Đăng kí thất bại",Toast.LENGTH_SHORT).show();

            }
        });
    }


}
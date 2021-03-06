package com.example.easyapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.easyapp.R;
import com.example.easyapp.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    private Spinner sp;
    private FirebaseDatabase database;

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
        sp = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Kh??ch h??ng","T??i x???"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(sp.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        sp.setAdapter(adapter);
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

                        }else{
                            regPassword.setError("M???t kh???u kh??ng kh???p");
                        }
                    }
                }
                else{
                    Toast.makeText(SignUpActivity.this,"B???n ch??a nh???p ?????y ????? th??ng tin",Toast.LENGTH_SHORT).show();
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

        String passPattern =  "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        String phonePattern = "[0-9]";
        String confirmpassPattern ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";


        if(!emailz.matches(emailPattern)){
            regEmail.setError("Email b???n nh???p kh??ng h???p l??? !");
            return false;
        }
        if(!userz.matches(userPattern)){
            regUsername.setError("UserName b???n nh???p kh??ng h???p l??? !");
            return false;
        }
        if(!passz.matches(passPattern)){
            regPassword.setError("M???t kh???u ph???i ch???a ????? (k?? t??? hoa, th?????ng, s??? v?? k?? t??? ?????c bi???t) !");
            return false;
        }
        if(!phonez.matches(phonePattern) && phonez.length() != 10){
            regPhoneNo.setError("S??? ??i???n tho???i kh??ng ch??? (ch???, k?? t??? v?? 10 s???) !");
            return false;
        }
        if(!confirmpassz.matches(confirmpassPattern)){
            regConfirmPassword.setError("M???t kh???u ph???i ch???a ????? (k?? t??? hoa, th?????ng, s??? v?? k?? t??? ?????c bi???t) !");
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
                            GetInfo(regName.getEditText().getText().toString(),regUsername.getEditText().getText().toString(),regEmail.getEditText().getText().toString(),regPhoneNo.getEditText().getText().toString(),sp.getSelectedItem().toString());
                        } else {
                            // If sign in fails, display a message to the user.
                            //Toast.makeText(SignUpActivity.this,"????ng k?? th???t b???i",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void GetInfo(String Name,String UserName, String Email, String Phone,String Role) {

        Map<String,String> data = new HashMap<>();
        data.put("Name:",Name);
        data.put("UserName:",UserName);
        data.put("Email:",Email);
        data.put("Phone:",Phone);
        data.put("Role",Role);

        String id = mAuth.getCurrentUser().getUid();

        UserModel um = new UserModel();
        um.setEmail(Email);
        um.setName(Name);
        um.setPhone(Phone);
        um.setUsername(UserName);
        um.setUserid(id);
        um.setRole(Role);
        um.setAddress("");
        reference = FirebaseDatabase.getInstance().getReference("User").child(id);
        reference.setValue(um);
        db.collection("Users").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Toast.makeText(SignUpActivity.this,"????ng k?? th??nh c??ng",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this,"????ng k?? th???t b???i",Toast.LENGTH_SHORT).show();

            }
        });
    }


}
package com.example.easyapp.ui.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.easyapp.R;
import com.example.easyapp.model.UserModel;
import com.example.easyapp.ui.ForgotActivity;
import com.example.easyapp.ui.HomeActivity;
import com.example.easyapp.ui.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AccountFragment extends Fragment {
    private View v;
    private EditText profile_name, profile_email, profile_phone, profile_address;
    private Button update, signout;

    private FirebaseDatabase database;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    public AccountFragment() {
        // Required empty public constructor
    }

//    public static AccountFragment newInstance(String param1, String param2) {
//
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_account, container, false);
        initUI();
        getInfo();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo();
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity().getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
    private void initUI(){
        profile_name = v.findViewById(R.id.profile_Name);
        profile_email = v.findViewById(R.id.profile_Email);
        profile_phone = v.findViewById(R.id.profile_Phone);
        profile_address = v.findViewById(R.id.profile_Address);
        update = v.findViewById(R.id.Update);
        signout = v.findViewById(R.id.Signout);
    }

    private void getInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();

            mDatabase.child("User").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Lấy dữ liệu thất bại", task.getException());
                    }
                    else {
                        UserModel um = task.getResult().getValue(UserModel.class);
                        profile_name.setText(um.getName());
                        profile_email.setText(um.getEmail());
                        profile_phone.setText(um.getPhone());
                        profile_address.setText(um.getAddress());
                    }
                }
            });
        }
    }

    private void updateInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();


            String name = profile_name.getText().toString().trim();
            String email = profile_email.getText().toString().trim();
            String phone = profile_phone.getText().toString().trim();
            String address = profile_address.getText().toString().trim();

            mDatabase.child("User").child(uid).child("name").setValue(name);
            mDatabase.child("User").child(uid).child("email").setValue(email);
            mDatabase.child("User").child(uid).child("phone").setValue(phone);
            mDatabase.child("User").child(uid).child("address").setValue(address);


            Intent intent= new Intent(getActivity().getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    }
}
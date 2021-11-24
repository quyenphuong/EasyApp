package com.example.easyapp.ui.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyapp.R;
import com.example.easyapp.ui.ForgotActivity;
import com.example.easyapp.ui.LoginActivity;


public class AccountFragment extends Fragment {

//    private EditText profile_name, profile_email, profile_phone, profile_address;
//    private Button update, signout;



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

        View v = inflater.inflate(R.layout.fragment_account, container, false);
//        editText = v.findViewById(R.id.profile_name);

        return v;
    }
}
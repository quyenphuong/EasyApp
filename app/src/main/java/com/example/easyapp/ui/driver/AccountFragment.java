package com.example.easyapp.ui.driver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.easyapp.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {

//    CircleImageView circleimageview;
//    EditText editText;
//    Button buttonUpdate;

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
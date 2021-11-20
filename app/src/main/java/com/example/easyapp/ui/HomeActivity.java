package com.example.easyapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.easyapp.R;
import com.example.easyapp.ui.driver.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //ẩn statusbar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Fragment fragment=null;
                switch (item.getItemId()){

                    case R.id.nav_home:
                        fragment= new com.example.easyapp.ui.driver.HomeFragment();
                        break;
                    case R.id.nav_activity:
                        fragment= new com.example.easyapp.ui.driver.ActivityFragment();
                        break;
                    case R.id.nav_pay:
                        fragment= new com.example.easyapp.ui.driver.PayFragment();
                        break;
                    case R.id.nav_chat:
                        fragment= new com.example.easyapp.ui.driver.ChatFragment();
                        break;
                    case R.id.nav_account:
                        fragment= new com.example.easyapp.ui.driver.AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                return true;
            }
        });
    }
}
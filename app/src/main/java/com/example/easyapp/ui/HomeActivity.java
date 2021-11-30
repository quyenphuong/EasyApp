package com.example.easyapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.example.easyapp.R;
import com.example.easyapp.adapters.ViewPagerAdapter;
import com.example.easyapp.ui.customer.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager2 viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //ẩn statusbar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        navigationView = findViewById(R.id.bottom_navigation);
//        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        viewpager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewpager.setAdapter(viewPagerAdapter);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                int id = item.getItemId();
                if(id == R.id.nav_home){
                    viewpager.setCurrentItem(0);

                }else if(id == R.id.nav_activity){
                    viewpager.setCurrentItem(1);
                }else if(id == R.id.nav_pay){
                    viewpager.setCurrentItem(2);
                }else if(id == R.id.nav_chat){
                    viewpager.setCurrentItem(3);
                }else if(id == R.id.nav_account){
                    viewpager.setCurrentItem(4);
                }
                return true;
            }
        });

        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.nav_activity).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.nav_pay).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.nav_chat).setChecked(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.nav_account).setChecked(true);
                        break;

                }
            }
        });
    }
}
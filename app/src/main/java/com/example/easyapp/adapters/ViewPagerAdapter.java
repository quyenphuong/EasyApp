package com.example.easyapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.easyapp.ui.customer.AccountFragment;
import com.example.easyapp.ui.customer.ActivityFragment;
import com.example.easyapp.ui.customer.ChatFragment;
import com.example.easyapp.ui.customer.HomeFragment;
import com.example.easyapp.ui.customer.PayFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ActivityFragment();
            case 2:
                return new PayFragment();
            case 3:
                return new ChatFragment();
            case 4:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

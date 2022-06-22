package com.example.btl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.btl.HomeActivity;
import com.example.btl.fragment.AccountFragment;
import com.example.btl.fragment.CartsFragment;
import com.example.btl.fragment.HomeFragment;
import com.example.btl.fragment.OrdersFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    private int numberPager = 4;
    private HomeActivity homeActivity;

    public FragmentAdapter(@NonNull FragmentManager fm, int numberPager, HomeActivity homeActivity) {
        super(fm, numberPager);
        this.homeActivity = homeActivity;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new HomeFragment(homeActivity);
            case 1:
                return new CartsFragment(homeActivity);
            case 2:
                return new OrdersFragment(homeActivity);
            case 3:
                return new AccountFragment(homeActivity);
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberPager;
    }
}

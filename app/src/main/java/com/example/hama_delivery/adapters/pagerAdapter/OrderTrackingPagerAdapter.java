package com.example.hama_delivery.adapters.pagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hama_delivery.fragment.UserInformationFragment;
import com.example.hama_delivery.fragment.VendorInformationFragment;

public class OrderTrackingPagerAdapter extends FragmentPagerAdapter {
    private int number_of_Tab;

    public OrderTrackingPagerAdapter(@NonNull FragmentManager fm, int number_of_Tab) {
        super(fm);
        this.number_of_Tab = number_of_Tab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new VendorInformationFragment();
            case 1:
                return new UserInformationFragment();

            default:
                return new VendorInformationFragment();


        }    }

    @Override
    public int getCount() {
        return number_of_Tab;
    }
}


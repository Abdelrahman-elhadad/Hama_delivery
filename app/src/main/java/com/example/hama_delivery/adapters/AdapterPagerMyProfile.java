package com.example.hama_delivery.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hama_delivery.fragment.ActiveOrderFragment;
import com.example.hama_delivery.fragment.PendingFragment;
import com.example.hama_delivery.fragment.ReturnedFragment;

public class AdapterPagerMyProfile   extends FragmentPagerAdapter {
    private int number_of_Tab;

    public AdapterPagerMyProfile(@NonNull FragmentManager fm, int number_of_Tab) {
        super(fm);
        this.number_of_Tab = number_of_Tab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ActiveOrderFragment();
            case 1:
                return new PendingFragment();
            case 2:
                return new ReturnedFragment();
            default:
                return new ActiveOrderFragment();


        }    }

    @Override
    public int getCount() {
        return number_of_Tab;
    }
}

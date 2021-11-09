package com.example.hama_delivery.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hama_delivery.fragment.ActiveOrderFragment;

public class AdapterPagerMyOrder  extends FragmentPagerAdapter {
    private int number_of_Tab;

    public AdapterPagerMyOrder(@NonNull FragmentManager fm, int number_of_Tab) {
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

               // return new PendingFragment();
            case 2:
             //   return new CanceledFragment();
            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return number_of_Tab;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Complited";
            case 1:
                return   "Pending";
            case 3:
                return "canceled";
            default:
                return "canceled";
        }
    }

//    public AdapterPagerMyOrder(@NonNull FragmentManager fm, int behavior, int number_of_Tab) {
//        super(fm, behavior);
//        this.number_of_Tab = number_of_Tab;
//    }
}

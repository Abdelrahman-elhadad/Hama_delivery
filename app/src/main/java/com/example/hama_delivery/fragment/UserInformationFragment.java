package com.example.hama_delivery.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.hama_delivery.base.BaseFragment;
import com.example.hama_delivery.R;
import com.example.hama_delivery.store.pending_order_array;

import java.util.ArrayList;

public class UserInformationFragment extends BaseFragment {
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    ImageView imageback;
    Button orderTracking;
    ViewPager viewPager;
    TextView txt_toolbar;
    LinearLayout parent_my_order;
    RecyclerView pending_rv;
    ArrayList<pending_order_array> pending_array;

    public UserInformationFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.user_information_fragment, container, false);
        fragmentManager = getChildFragmentManager();

        return view;
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();


    }


}

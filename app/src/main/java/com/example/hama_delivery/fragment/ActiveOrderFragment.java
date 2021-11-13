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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.hama_delivery.BaseFragment;
import com.example.hama_delivery.R;
import com.example.hama_delivery.adapters.ActiveOrderAdapter;
import com.example.hama_delivery.store.Active_order_array;

import java.util.ArrayList;

public class ActiveOrderFragment extends BaseFragment {
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    ImageView imageback;
    Button orderTracking;
    ViewPager viewPager;
    TextView txt_toolbar;
    LinearLayout parent_my_order;
    RecyclerView active_order_rv;
    ArrayList<Active_order_array> active_order_arrays;


    public ActiveOrderFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.active_order_fragment, container, false);
        fragmentManager=getChildFragmentManager();
        active_order_rv=(RecyclerView)view.findViewById(R.id.active_order_rv);
        active_order_rv.setLayoutManager(new LinearLayoutManager(requireContext()));


        active_order_arrays = new ArrayList<>();

        Active_order_array bs = new Active_order_array(R.drawable.khateem ,"sbrena");
        Active_order_array bs1 = new Active_order_array(R.drawable.khateem ,"sbrena");
        Active_order_array bs2 = new Active_order_array(R.drawable.khateem ,"sbrena");
        Active_order_array bs3 = new Active_order_array(R.drawable.khateem ,"sbrena");

        active_order_arrays.add(bs);
        active_order_arrays.add(bs1);
        active_order_arrays.add(bs2);
        active_order_arrays.add(bs3);
        ActiveOrderAdapter activeOrderAdapter = new ActiveOrderAdapter(active_order_arrays,"active ordr");
        active_order_rv.setAdapter(activeOrderAdapter);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();


    }
}

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

import com.example.hama_delivery.base.BaseFragment;
import com.example.hama_delivery.R;
import com.example.hama_delivery.adapters.PendingOrderAdapter;
import com.example.hama_delivery.store.pending_order_array;

import java.util.ArrayList;

public class PendingFragment extends BaseFragment {
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    ImageView imageback;
    Button orderTracking;
    ViewPager viewPager;
    TextView txt_toolbar;
    LinearLayout parent_my_order;
    RecyclerView pending_rv;
    ArrayList<pending_order_array> pending_array;

    public PendingFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.pending_order_fragment, container, false);
        fragmentManager = getChildFragmentManager();
        pending_rv = (RecyclerView) view.findViewById(R.id.pending_rv);
        pending_rv.setLayoutManager(new LinearLayoutManager(requireContext()));


        pending_array = new ArrayList<>();
        pending_order_array bs = new pending_order_array(R.drawable.khateem, "sbrena");
        pending_order_array bs1 = new pending_order_array(R.drawable.khateem, "sbrena");
        pending_order_array bs2 = new pending_order_array(R.drawable.khateem, "sbrena");
        pending_order_array bs3 = new pending_order_array(R.drawable.khateem, "sbrena");

        pending_array.add(bs);
        pending_array.add(bs1);
        pending_array.add(bs2);
        pending_array.add(bs3);
        PendingOrderAdapter pendingOrderAdapter = new PendingOrderAdapter(pending_array, "active ordr");
        pending_rv.setAdapter(pendingOrderAdapter);
        return view;
    }



        @Override
        public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            fragmentManager = getFragmentManager();


        }


    }

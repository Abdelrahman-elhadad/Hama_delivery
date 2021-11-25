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
import com.example.hama_delivery.adapters.Vendor_info_adapter;
import com.example.hama_delivery.store.vendor_info;

import java.util.ArrayList;

public class VendorInformationFragment extends BaseFragment {
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    ImageView imageback;
    Button orderTracking;
    ViewPager viewPager;
    TextView txt_toolbar;
    LinearLayout parent_my_order;
    RecyclerView vendor_info_rv;
    ArrayList<vendor_info> vendor_infoArrayList;

    public VendorInformationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.vendors_information_fragment, container, false);
        fragmentManager = getChildFragmentManager();
        vendor_info_rv = (RecyclerView) view.findViewById(R.id.vendor_info_rv);
        vendor_info_rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        vendor_infoArrayList = new ArrayList<>();
        vendor_info bs = new vendor_info(R.drawable.khateem, "sbrena");
        vendor_info bs1 = new vendor_info(R.drawable.khateem, "sbrena");
        vendor_info bs2 = new vendor_info(R.drawable.khateem, "sbrena");
        vendor_info bs3 = new vendor_info(R.drawable.khateem, "sbrena");

        vendor_infoArrayList.add(bs);
        vendor_infoArrayList.add(bs1);
        vendor_infoArrayList.add(bs2);
        vendor_infoArrayList.add(bs3);

        Vendor_info_adapter vendor_info_adapter = new Vendor_info_adapter(vendor_infoArrayList, "active ordr");
        vendor_info_rv.setAdapter(vendor_info_adapter);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();


    }
    }

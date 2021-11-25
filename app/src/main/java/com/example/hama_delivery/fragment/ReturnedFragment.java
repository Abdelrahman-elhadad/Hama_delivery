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
import com.example.hama_delivery.adapters.ReturnedOrderAdapter;
import com.example.hama_delivery.store.canceled_order_array;

import java.util.ArrayList;

public class ReturnedFragment extends BaseFragment {
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    ImageView imageback;
    Button orderTracking;
    ViewPager viewPager;
    TextView txt_toolbar;
    LinearLayout parent_my_order;
    RecyclerView returned_rv;
    ArrayList<canceled_order_array> returned_array;

    public ReturnedFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.returned_order_fragment, container, false);
        fragmentManager = getChildFragmentManager();
        returned_rv = (RecyclerView) view.findViewById(R.id.returned_rv);
        returned_rv.setLayoutManager(new LinearLayoutManager(requireContext()));


        returned_array = new ArrayList<>();
        canceled_order_array bs = new canceled_order_array(R.drawable.khateem, "sbrena");
        canceled_order_array bs1 = new canceled_order_array(R.drawable.khateem, "sbrena");
        canceled_order_array bs2 = new canceled_order_array(R.drawable.khateem, "sbrena");
        canceled_order_array bs3 = new canceled_order_array(R.drawable.khateem, "sbrena");

        returned_array.add(bs);
        returned_array.add(bs1);
        returned_array.add(bs2);
        returned_array.add(bs3);
        ReturnedOrderAdapter returend = new ReturnedOrderAdapter(returned_array, "active ordr");
        returned_rv.setAdapter(returend);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();


    }

}

package com.example.hama_delivery;

import android.graphics.Typeface;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.hama_delivery.adapters.AdapterPagerMyOrder;
import com.example.hama_delivery.base.BaseFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyOrder  extends BaseFragment {

    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    ImageView imageback;
    Button orderTracking;
    ViewPager viewPager;
    TextView txt_toolbar;
    LinearLayout parent_my_order;
    TabLayout tabLayout;
    TabItem active_order, pending, canceled;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  return inflater.inflate(R.layout.active_order, container, false);

        View view = inflater.inflate(R.layout.active_order, container, false);

        fragmentManager = getChildFragmentManager();
        tabLayout = (TabLayout) view.findViewById(R.id.tab_my_order);
        active_order = (TabItem) view.findViewById(R.id.active_orderr);
        pending = (TabItem) view.findViewById(R.id.pending_order_tab);
        canceled = (TabItem) view.findViewById(R.id.cancel_tab);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager_myorder);

//           AdapterPagerMyOrder adapterPagerMyOrder = new AdapterPagerMyOrder(fragmentManager, tabLayout.getTabCount());
        fragmentManager=getChildFragmentManager();
        AdapterPagerMyOrder adapterPagerMyOrder = new AdapterPagerMyOrder(fragmentManager,tabLayout.getTabCount());
        viewPager.setAdapter(adapterPagerMyOrder);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabIndicatorFullWidth(false);
        tabLayout.getTabAt(0).setText(R.string.Completed);
        tabLayout.getTabAt(1).setText(R.string.pending);
        tabLayout.getTabAt(2).setText(R.string.canceled);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_title);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //  textView.setTextColor(tabLayout.getTabTextColors());
                textView.setTypeface(Typeface.DEFAULT_BOLD);

                tabLayout.setTabIndicatorFullWidth(false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_title);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTypeface(Typeface.DEFAULT);
                textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_store_page));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();


    }
}

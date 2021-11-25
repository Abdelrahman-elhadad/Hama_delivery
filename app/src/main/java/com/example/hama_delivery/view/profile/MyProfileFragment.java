package com.example.hama_delivery.view.profile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.example.hama_delivery.R;
import com.example.hama_delivery.adapters.AdapterPagerMyProfile;
import com.example.hama_delivery.base.BaseFragment;
import com.example.hama_delivery.databinding.MyProfileBinding;
import com.example.hama_delivery.listener.LoginListener;
import com.example.hama_delivery.utils.SharedPreferenceConstant;
import com.example.hama_delivery.utils.image.CircleTransform;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class MyProfileFragment extends BaseFragment {
    FragmentManager fragmentManager;
    MyProfileBinding binding;
    ProfileViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  return inflater.inflate(R.layout.active_order, container, false);
        binding = MyProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ProfileViewModel(requireContext(),null);
        binding.setModel(model);
        fragmentManager = getChildFragmentManager();

        AdapterPagerMyProfile adapterPagerMyProfile = new AdapterPagerMyProfile(fragmentManager, binding.tabMyProfile.getTabCount());
        binding.viewPagerMyorder.setAdapter(adapterPagerMyProfile);

        binding.tabMyProfile.setupWithViewPager(binding.viewPagerMyorder);

        binding.tabMyProfile.setTabIndicatorFullWidth(false);
        binding.tabMyProfile.getTabAt(0).setText(R.string.Completed);
        binding.tabMyProfile.getTabAt(1).setText(R.string.pending);
        binding.tabMyProfile.getTabAt(2).setText(R.string.canceled);
        binding.tabMyProfile.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_title);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //  textView.setTextColor(tabLayout.getTabTextColors());
                textView.setTypeface(Typeface.DEFAULT_BOLD);

                binding.tabMyProfile.setTabIndicatorFullWidth(false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.text_my_profile);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTypeface(Typeface.DEFAULT);
                textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_store_page));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        String avatar=SharedPreferenceConstant.getSharedPreferenceUser(requireContext()).getAvatar();
        if (avatar!= null && !avatar.trim().isEmpty()) {
            Picasso.get().load(avatar).placeholder(R.drawable.ic_default_user).error(R.drawable.ic_default_user).transform(new CircleTransform()).into(binding.ivProfile);
        } else
            Picasso.get().load(R.drawable.ic_default_user).placeholder(R.drawable.ic_default_user).error(R.drawable.ic_default_user).into(binding.ivProfile);


    }

}

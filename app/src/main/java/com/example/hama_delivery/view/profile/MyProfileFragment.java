package com.example.hama_delivery.view.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.hama_delivery.R;
import com.example.hama_delivery.adapters.pagerAdapter.OrderPagerAdapter;
import com.example.hama_delivery.base.BaseFragment;
import com.example.hama_delivery.databinding.MyProfileBinding;
import com.example.hama_delivery.utils.SharedPreferenceConstant;
import com.example.hama_delivery.utils.image.CircleTransform;
import com.squareup.picasso.Picasso;

public class MyProfileFragment extends BaseFragment {
    FragmentManager fragmentManager;
    MyProfileBinding binding;
    ProfileViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MyProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ProfileViewModel(requireContext(),null);
        binding.setModel(model);
        fragmentManager = getChildFragmentManager();

        OrderPagerAdapter adapterPagerMyProfile = new OrderPagerAdapter(fragmentManager, binding.tabMyProfile.getTabCount(),requireContext());
        binding.viewPagerMyorder.setAdapter(adapterPagerMyProfile);
        binding.tabMyProfile.setupWithViewPager(binding.viewPagerMyorder);

        String avatar= SharedPreferenceConstant.getSharedPreferenceUser(requireContext()).getAvatar();
        if (avatar!= null && !avatar.trim().isEmpty()) {
            Picasso.get().load(avatar).placeholder(R.drawable.ic_default_user).error(R.drawable.ic_default_user).transform(new CircleTransform()).into(binding.ivProfile);
        } else
            Picasso.get().load(R.drawable.ic_default_user).placeholder(R.drawable.ic_default_user).error(R.drawable.ic_default_user).into(binding.ivProfile);
    }

}

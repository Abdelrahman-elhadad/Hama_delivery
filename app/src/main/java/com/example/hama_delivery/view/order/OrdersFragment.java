package com.example.hama_delivery.view.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hama_delivery.HomeActivity;
import com.example.hama_delivery.OrderListener;
import com.example.hama_delivery.R;
import com.example.hama_delivery.adapters.orderActive.ActiveOrderAdapter;
import com.example.hama_delivery.adapters.orderPending.PendingOrderAdapter;
import com.example.hama_delivery.base.BaseFragment;
import com.example.hama_delivery.databinding.OrdersFragmentBinding;
import com.example.hama_delivery.dialog.LoginDialog;
import com.example.hama_delivery.model.order.Order;
import com.example.hama_delivery.utils.AppConstants;
import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

public class OrdersFragment extends BaseFragment implements OrderListener {


    OrdersFragmentBinding binding;
    OrderViewModel model;
    private String status;
    private Skeleton skeleton;

    public static OrdersFragment newInstance(String status) {
        OrdersFragment fragment = new OrdersFragment();
        fragment.setStatus(status);
        return fragment;
    }

    public OrdersFragment() {
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = OrdersFragmentBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new OrderViewModel(requireContext());
        binding.setModel(model);

        binding.rvOrders.setLayoutManager(new LinearLayoutManager(requireContext()));
        skeleton = SkeletonLayoutUtils.applySkeleton(binding.rvOrders, R.layout.card_order_active, 2);


        model.getOrdersObservable().observe(requireActivity(), ordersResponse -> {

            skeleton.showOriginal();
            if (ordersResponse.isStatus()) {

                if (ordersResponse.getData().isEmpty()) {

                    binding.rvOrders.setVisibility(View.GONE);
                    binding.llNoOrder.setVisibility(View.VISIBLE);
                } else {
                    binding.rvOrders.setVisibility(View.VISIBLE);
                    binding.llNoOrder.setVisibility(View.GONE);
                    if (status.equalsIgnoreCase(AppConstants.ACTIVE)) {
                        ActiveOrderAdapter adapter = new ActiveOrderAdapter(ordersResponse.getData(), OrdersFragment.this);
                        binding.rvOrders.setAdapter(adapter);
                    }else if (status.equalsIgnoreCase(AppConstants.PENDING)) {
                        PendingOrderAdapter adapter = new PendingOrderAdapter(ordersResponse.getData(), OrdersFragment.this,requireActivity());
                        binding.rvOrders.setAdapter(adapter);
                    }
                }
            } else {
                if (ordersResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog loginDialog = LoginDialog.newInstance();
                    loginDialog.show(getChildFragmentManager(), "login");
                } else
                    Snackbar.make(binding.rvOrders, ordersResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });

        binding.rvOrders.setVisibility(View.VISIBLE);
        binding.llNoOrder.setVisibility(View.GONE);
        skeleton.showSkeleton();
        model.getOrders(status);

    }

    @Override
    public void onOrderClick(Order order, int position) {
        if (getActivity() != null)
            ((HomeActivity) getActivity()).openOrderTrack();
    }
}

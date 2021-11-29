package hama.alsaygh.kw.delivery.view.order.orderDetails.userInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import hama.alsaygh.kw.delivery.adapters.vendorInfo.VendorInfoAdapter;
import hama.alsaygh.kw.delivery.adapters.vendorProductItem.UserProductAdapter;
import hama.alsaygh.kw.delivery.databinding.UserInformationFragmentBinding;
import hama.alsaygh.kw.delivery.databinding.VendorsInformationFragmentBinding;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.view.base.BaseFragment;

public class UserInformationFragment extends BaseFragment {
    FragmentManager fragmentManager;

    UserInformationFragmentBinding binding;
    UserInfoViewModel model;
    Order order;
    String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static UserInformationFragment newInstance(Order order, String status) {
        UserInformationFragment fragment = new UserInformationFragment();
        fragment.setOrder(order);
        fragment.setStatus(status);
        return fragment;
    }

    public UserInformationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserInformationFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new UserInfoViewModel(requireContext(),order,status);
        binding.setModel(model);

        UserProductAdapter adapter=new UserProductAdapter(order.getItems());
        binding.rvItems.setAdapter(adapter);
        binding.rvItems.setLayoutManager(new LinearLayoutManager(requireContext()));


    }
}

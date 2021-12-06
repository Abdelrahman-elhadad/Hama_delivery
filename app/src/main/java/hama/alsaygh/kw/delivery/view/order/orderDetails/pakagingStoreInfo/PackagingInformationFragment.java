package hama.alsaygh.kw.delivery.view.order.orderDetails.pakagingStoreInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.delivery.adapters.vendorInfo.VendorInfoAdapter;
import hama.alsaygh.kw.delivery.databinding.PackageInformationFragmentBinding;
import hama.alsaygh.kw.delivery.databinding.VendorsInformationFragmentBinding;
import hama.alsaygh.kw.delivery.dialog.LoginDialog;
import hama.alsaygh.kw.delivery.listener.OrderListener;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.model.store.Store;
import hama.alsaygh.kw.delivery.utils.AppConstants;
import hama.alsaygh.kw.delivery.view.base.BaseFragment;
import hama.alsaygh.kw.delivery.view.qr.QRScannerActivity;

public class PackagingInformationFragment extends BaseFragment implements OrderListener {
    FragmentManager fragmentManager;

   PackageInformationFragmentBinding binding;
    PackageViewModel model;
    Order order;
    String status;

    int vendor_id = -1;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static PackagingInformationFragment newInstance(Order order, String status) {
        PackagingInformationFragment fragment = new PackagingInformationFragment();
        fragment.setOrder(order);
        fragment.setStatus(status);
        return fragment;
    }

    public PackagingInformationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PackageInformationFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new PackageViewModel(requireContext(), order, status);
        binding.setModel(model);


        VendorInfoAdapter adapter = new VendorInfoAdapter(order.getPackaging_store_info(), this);
        binding.rvItems.setAdapter(adapter);
        binding.rvItems.setLayoutManager(new LinearLayoutManager(requireContext()));

        model.getQrObserver().observe(requireActivity(), checkResponse -> {
            if (checkResponse.isStatus()) {
                Snackbar.make(binding.getRoot(), checkResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(requireContext(), QRScannerActivity.class);
                intent.putExtra(AppConstants.ORDER_ID, order.getId());
                intent.putExtra(AppConstants.VENDOR_ID, vendor_id);
                startActivity(intent);
            } else {
                if (checkResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog loginDialog = LoginDialog.newInstance();
                    loginDialog.show(getChildFragmentManager(), "login");
                } else
                    Snackbar.make(binding.getRoot(), checkResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });


        if(order.getPackaging_store_info().isEmpty())
        {
          binding.nsMain.setVisibility(View.GONE);
            binding.llNoPackagingStore.setVisibility(View.VISIBLE);
        }else {
            binding.nsMain.setVisibility(View.VISIBLE);
            binding.llNoPackagingStore.setVisibility(View.GONE);
        }
    }

    @Override
    public void onOrderClick(Order order, int position) {

    }

    @Override
    public void onOrderQrReceivedClick(Store store, int position) {
        vendor_id = store.getId();
        model.getQr(order.getId(), vendor_id);
    }

    @Override
    public void refresh() {

    }
}

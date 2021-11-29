package hama.alsaygh.kw.delivery.view.order.orderDetails.vendorInfo;

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
import hama.alsaygh.kw.delivery.databinding.VendorsInformationFragmentBinding;
import hama.alsaygh.kw.delivery.dialog.LoginDialog;
import hama.alsaygh.kw.delivery.dialog.VendorsDialog;
import hama.alsaygh.kw.delivery.listener.OrderListener;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.model.store.Store;
import hama.alsaygh.kw.delivery.utils.AppConstants;
import hama.alsaygh.kw.delivery.view.QR.QRScannerActivity;
import hama.alsaygh.kw.delivery.view.base.BaseFragment;

public class VendorInformationFragment extends BaseFragment implements OrderListener {
    FragmentManager fragmentManager;

    VendorsInformationFragmentBinding binding;
    VendorViewModel model;
    Order order;
    String status;

    int vendor_id = -1;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static VendorInformationFragment newInstance(Order order, String status) {
        VendorInformationFragment fragment = new VendorInformationFragment();
        fragment.setOrder(order);
        fragment.setStatus(status);
        return fragment;
    }

    public VendorInformationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = VendorsInformationFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new VendorViewModel(requireContext(), order, status);
        binding.setModel(model);

        // Vendor_info_adapter vendor_info_adapter = new Vendor_info_adapter(vendor_infoArrayList, "active ordr");

        VendorInfoAdapter adapter = new VendorInfoAdapter(order.getVendor_info(), this);
        binding.rvItems.setAdapter(adapter);
        binding.rvItems.setLayoutManager(new LinearLayoutManager(requireContext()));

        model.getQrObserver().observe(requireActivity(), checkResponse -> {
            if (checkResponse.isStatus()) {
                Snackbar.make(binding.llScanToReceive, checkResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(requireContext(), QRScannerActivity.class);
                intent.putExtra(AppConstants.ORDER_ID, order.getId());
                intent.putExtra(AppConstants.VENDOR_ID, vendor_id);
                startActivity(intent);
            } else {
                if (checkResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog loginDialog = LoginDialog.newInstance();
                    loginDialog.show(getChildFragmentManager(), "login");
                } else
                    Snackbar.make(binding.llScanToReceive, checkResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.llScanToReceive.setOnClickListener(v -> {
            VendorsDialog vendorsDialog = VendorsDialog.newInstance(order.getVendor_info(), VendorInformationFragment.this);
            vendorsDialog.show(getChildFragmentManager(), "vendors");
        });

        binding.llScanToDelivered.setOnClickListener(v -> {


        });
    }

    @Override
    public void onOrderClick(Order order, int position) {

    }

    @Override
    public void onOrderQrReceivedClick(Store store, int position) {
        vendor_id = store.getId();
        model.getQr(order.getId(), vendor_id);
    }
}

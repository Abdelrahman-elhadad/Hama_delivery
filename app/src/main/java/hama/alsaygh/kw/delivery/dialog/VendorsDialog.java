package hama.alsaygh.kw.delivery.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.adapters.vendorInfo.VendorInfoAdapter;
import hama.alsaygh.kw.delivery.adapters.vendorInfo.VendorInfoDialogAdapter;
import hama.alsaygh.kw.delivery.listener.OrderListener;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.model.store.Store;
import hama.alsaygh.kw.delivery.view.auth.LoginActivity;


public class VendorsDialog extends BottomSheetDialogFragment implements OrderListener{
    public static final String TAG = "ActionBottomDialog";
    private OrderListener mListener;
    private List<Store> stores;

    public static VendorsDialog newInstance(List<Store> stores,OrderListener mListener) {
        VendorsDialog vendorsDialog=new VendorsDialog();
        vendorsDialog.setmListener(mListener);
        vendorsDialog.setStores(stores);
        return vendorsDialog;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public void setmListener(OrderListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        this.setCancelable(true);

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.vendor_dialog, null);


        RecyclerView rv_vendor = view.findViewById(R.id.rv_vendor);


        VendorInfoDialogAdapter adapter=new VendorInfoDialogAdapter(stores,this);
        rv_vendor.setAdapter(adapter);
        rv_vendor.setLayoutManager(new LinearLayoutManager(requireContext()));

        dialog.setContentView(view);
        this.setCancelable(true);

    }

    @Override
    public void onOrderClick(Order order, int position) {

    }

    @Override
    public void onOrderQrReceivedClick(Store store, int position) {

        if(mListener!=null)
            mListener.onOrderQrReceivedClick(store,position);
        dismiss();
    }
}

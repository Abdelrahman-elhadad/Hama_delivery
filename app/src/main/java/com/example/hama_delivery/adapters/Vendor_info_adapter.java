package com.example.hama_delivery.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hama_delivery.store.pending_order_array;
import com.example.hama_delivery.store.vendor_info;

import java.util.ArrayList;

public class Vendor_info_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<vendor_info> vendor_infoArrayList;
    private String type;
    FragmentManager fragmentManager;
    Context context;
    FragmentTransaction fragmentTransaction;

    public Vendor_info_adapter(ArrayList<vendor_info> vendor_infoArrayList, String type) {
        this.vendor_infoArrayList = vendor_infoArrayList;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return vendor_infoArrayList.size();
    }
}

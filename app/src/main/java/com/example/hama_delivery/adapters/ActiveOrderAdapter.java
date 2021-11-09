package com.example.hama_delivery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hama_delivery.R;
import com.example.hama_delivery.store.Active_order_array;

import java.util.ArrayList;

public class ActiveOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Active_order_array> active_order_array;
    private String type;
    FragmentManager fragmentManager;
    Context context;
    FragmentTransaction fragmentTransaction;


    public ActiveOrderAdapter(ArrayList<Active_order_array> active_order_array, String type) {
        this.active_order_array = active_order_array;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_active_order,null,false);
        ActiveOrderAdapter.Holder holder = new ActiveOrderAdapter.Holder(v);
        return new  ActiveOrderAdapter.Holder(v);    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return active_order_array.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tv1;
        View view;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView =(ImageView)itemView.findViewById(R.id.ring1);
            imageView =(ImageView)itemView.findViewById(R.id.ring2);
            imageView =(ImageView)itemView.findViewById(R.id.ring3);
            imageView =(ImageView)itemView.findViewById(R.id.ring4);
            tv1=(TextView)itemView.findViewById(R.id.gold_ring11);

        }
    }
}

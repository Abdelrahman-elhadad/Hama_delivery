package com.example.hama_delivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hama_delivery.OrderTrackingListener;
import com.example.hama_delivery.R;
import com.example.hama_delivery.fragment.OrderTracking;
import com.example.hama_delivery.store.Active_order_array;

import java.util.ArrayList;

public class ActiveOrderAdapter extends RecyclerView.Adapter<ActiveOrderAdapter.Holder> {

    ArrayList<Active_order_array> active_order_array;
    private String type;
    FragmentManager fragmentManager;
    Context context;
    FragmentTransaction fragmentTransaction;
    OrderTrackingListener orderTrackingListener;


    public ActiveOrderAdapter(ArrayList<Active_order_array> active_order_array, String type ,OrderTrackingListener orderTrackingListener) {
        this.active_order_array = active_order_array;
        this.type = type;
        this.orderTrackingListener = orderTrackingListener;
    }

    @NonNull
    @Override
    public ActiveOrderAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_active_order,null,false);
        ActiveOrderAdapter.Holder holder = new ActiveOrderAdapter.Holder(v);
        return new  ActiveOrderAdapter.Holder(v);    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.out_of_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderTracking orderTracking = new OrderTracking();



            }
        });

    }


//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position,Holder holder1) {
//        holder1.out_of_delivery.setText("");
//
//
//    }

    @Override
    public int getItemCount() {
        return active_order_array.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv1 , out_of_delivery;
        View view;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView =(ImageView)itemView.findViewById(R.id.ring1);
            imageView =(ImageView)itemView.findViewById(R.id.ring2);
            imageView =(ImageView)itemView.findViewById(R.id.ring3);
            imageView =(ImageView)itemView.findViewById(R.id.ring4);
            tv1=(TextView)itemView.findViewById(R.id.gold_ring11);
            out_of_delivery=(TextView)itemView.findViewById(R.id.out_of_delivery);
            itemView.setOnClickListener((View.OnClickListener) this);


        }


        @Override
        public void onClick(View v) {
            orderTrackingListener.OrderTrackingListener(getAdapterPosition(),"tag");
        }
    }
}

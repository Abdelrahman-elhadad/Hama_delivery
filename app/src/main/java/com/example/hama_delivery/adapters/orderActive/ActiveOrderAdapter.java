package com.example.hama_delivery.adapters.orderActive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hama_delivery.OrderListener;
import com.example.hama_delivery.R;
import com.example.hama_delivery.databinding.CardOrderActiveBinding;
import com.example.hama_delivery.model.order.Order;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActiveOrderAdapter extends RecyclerView.Adapter<ActiveOrderAdapter.Holder> {

    private final List<Order> mValues;
    private final OrderListener listener;


    public ActiveOrderAdapter(List<Order> active_order_array, OrderListener listener) {
        this.mValues = active_order_array;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActiveOrderAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardOrderActiveBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_order_active, parent, false);
        return new ActiveOrderAdapter.Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int index) {
        final int position = index;
        holder.bind(new OrderActiveViewModel(holder.itemView.getContext(), mValues.get(position)));
        holder.itemView.setOnClickListener(v -> {
            if (listener != null)
                listener.onOrderClick(mValues.get(position), position);
        });

        if (mValues.get(position).getItems().isEmpty()) {
            holder.binding.ivItem1.setVisibility(View.GONE);
            holder.binding.ivItem2.setVisibility(View.GONE);
            holder.binding.ivItem3.setVisibility(View.GONE);
            holder.binding.ivItem4.setVisibility(View.GONE);
        } else if (mValues.get(position).getItems().size() == 1) {
            holder.binding.ivItem1.setVisibility(View.VISIBLE);
            holder.binding.ivItem2.setVisibility(View.GONE);
            holder.binding.ivItem3.setVisibility(View.GONE);
            holder.binding.ivItem4.setVisibility(View.GONE);


            String avatar = mValues.get(position).getItems().get(0).getProduct().getMedia().get(0).getLink();
            if (avatar != null && !avatar.trim().isEmpty()) {
                Picasso.get().load(avatar).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem1);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem1);


        } else if (mValues.get(position).getItems().size() == 2) {
            holder.binding.ivItem1.setVisibility(View.VISIBLE);
            holder.binding.ivItem2.setVisibility(View.GONE);
            holder.binding.ivItem3.setVisibility(View.VISIBLE);
            holder.binding.ivItem4.setVisibility(View.GONE);

            String avatar = mValues.get(position).getItems().get(0).getProduct().getMedia().get(0).getLink();
            if (avatar != null && !avatar.trim().isEmpty()) {
                Picasso.get().load(avatar).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem1);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem1);


            String avatar2 = mValues.get(position).getItems().get(1).getProduct().getMedia().get(0).getLink();
            if (avatar2 != null && !avatar2.trim().isEmpty()) {
                Picasso.get().load(avatar2).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem3);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem3);


        } else if (mValues.get(position).getItems().size() == 3) {
            holder.binding.ivItem1.setVisibility(View.VISIBLE);
            holder.binding.ivItem2.setVisibility(View.VISIBLE);
            holder.binding.ivItem3.setVisibility(View.VISIBLE);
            holder.binding.ivItem4.setVisibility(View.GONE);

            String avatar = mValues.get(position).getItems().get(0).getProduct().getMedia().get(0).getLink();
            if (avatar != null && !avatar.trim().isEmpty()) {
                Picasso.get().load(avatar).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem1);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem1);


            String avatar2 = mValues.get(position).getItems().get(1).getProduct().getMedia().get(0).getLink();
            if (avatar2 != null && !avatar2.trim().isEmpty()) {
                Picasso.get().load(avatar2).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem3);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem3);


            String avatar3 = mValues.get(position).getItems().get(2).getProduct().getMedia().get(0).getLink();
            if (avatar3 != null && !avatar3.trim().isEmpty()) {
                Picasso.get().load(avatar3).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem2);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem2);


        } else if (mValues.get(position).getItems().size() >= 4) {
            holder.binding.ivItem1.setVisibility(View.VISIBLE);
            holder.binding.ivItem2.setVisibility(View.VISIBLE);
            holder.binding.ivItem3.setVisibility(View.VISIBLE);
            holder.binding.ivItem4.setVisibility(View.VISIBLE);
            String avatar = mValues.get(position).getItems().get(0).getProduct().getMedia().get(0).getLink();
            if (avatar != null && !avatar.trim().isEmpty()) {
                Picasso.get().load(avatar).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem1);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem1);


            String avatar2 = mValues.get(position).getItems().get(1).getProduct().getMedia().get(0).getLink();
            if (avatar2 != null && !avatar2.trim().isEmpty()) {
                Picasso.get().load(avatar2).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem3);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem3);


            String avatar3 = mValues.get(position).getItems().get(2).getProduct().getMedia().get(0).getLink();
            if (avatar3 != null && !avatar3.trim().isEmpty()) {
                Picasso.get().load(avatar3).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem2);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem2);

            String avatar4 = mValues.get(position).getItems().get(3).getProduct().getMedia().get(0).getLink();
            if (avatar4 != null && !avatar4.trim().isEmpty()) {
                Picasso.get().load(avatar4).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivItem3);
            } else
                Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivItem3);

        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    protected class Holder extends RecyclerView.ViewHolder {
        final CardOrderActiveBinding binding;

        Holder(CardOrderActiveBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(OrderActiveViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}

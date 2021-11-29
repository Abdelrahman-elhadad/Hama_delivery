package hama.alsaygh.kw.delivery.adapters.vendorInfo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.adapters.vendorProductItem.VendorProductAdapter;
import hama.alsaygh.kw.delivery.databinding.CardVendorItemBinding;
import hama.alsaygh.kw.delivery.listener.OrderListener;
import hama.alsaygh.kw.delivery.model.store.Store;

public class VendorInfoAdapter extends RecyclerView.Adapter<VendorInfoAdapter.Holder> {

    private final List<Store> mValues;
    private final OrderListener listener;


    public VendorInfoAdapter(List<Store> active_order_array, OrderListener listener) {
        this.mValues = active_order_array;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VendorInfoAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardVendorItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_vendor_item, parent, false);
        return new VendorInfoAdapter.Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int index) {
        final int position = index;
        holder.bind(new VendorInfoViewModel(holder.itemView.getContext(), mValues.get(position)));
        holder.itemView.setOnClickListener(v -> {
            if(listener!=null)
                listener.onOrderQrReceivedClick(mValues.get(position),position);
        });

        VendorProductAdapter adapter = new VendorProductAdapter(mValues.get(position).getProducts());
        holder.binding.rvItems.setAdapter(adapter);
        holder.binding.rvItems.setLayoutManager(new LinearLayoutManager(holder.binding.rvItems.getContext()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    protected class Holder extends RecyclerView.ViewHolder {
        final CardVendorItemBinding binding;

        Holder(CardVendorItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(VendorInfoViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}

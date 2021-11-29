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
import hama.alsaygh.kw.delivery.databinding.CardVendorDialogItemBinding;
import hama.alsaygh.kw.delivery.databinding.CardVendorItemBinding;
import hama.alsaygh.kw.delivery.listener.OrderListener;
import hama.alsaygh.kw.delivery.model.store.Store;

public class VendorInfoDialogAdapter extends RecyclerView.Adapter<VendorInfoDialogAdapter.Holder> {

    private final List<Store> mValues;
    private final OrderListener listener;


    public VendorInfoDialogAdapter(List<Store> active_order_array, OrderListener listener) {
        this.mValues = active_order_array;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VendorInfoDialogAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardVendorDialogItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_vendor_dialog_item, parent, false);
        return new VendorInfoDialogAdapter.Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int index) {
        final int position = index;
        holder.bind(new VendorInfoViewModel(holder.itemView.getContext(), mValues.get(position)));
        holder.itemView.setOnClickListener(v -> {

            if(listener!=null)
                listener.onOrderQrReceivedClick(mValues.get(position),position);
        });

         }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    protected class Holder extends RecyclerView.ViewHolder {
        final CardVendorDialogItemBinding binding;

        Holder(CardVendorDialogItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(VendorInfoViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}

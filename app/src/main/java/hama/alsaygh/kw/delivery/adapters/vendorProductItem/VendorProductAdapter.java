package hama.alsaygh.kw.delivery.adapters.vendorProductItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.adapters.vendorInfo.VendorInfoViewModel;
import hama.alsaygh.kw.delivery.databinding.CardVendorItemBinding;
import hama.alsaygh.kw.delivery.databinding.CardVendorProductItemBinding;
import hama.alsaygh.kw.delivery.listener.OrderListener;
import hama.alsaygh.kw.delivery.model.cart.CartItem;
import hama.alsaygh.kw.delivery.model.store.Store;

public class VendorProductAdapter extends RecyclerView.Adapter<VendorProductAdapter.Holder> {

    private final List<CartItem> mValues;


    public VendorProductAdapter(List<CartItem> active_order_array) {
        this.mValues = active_order_array;
    }

    @NonNull
    @Override
    public VendorProductAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardVendorProductItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_vendor_product_item, parent, false);
        return new VendorProductAdapter.Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int index) {
        final int position = index;
        holder.bind(new VendorProductViewModel(holder.itemView.getContext(), mValues.get(position)));

        String avatar = mValues.get(position).getProduct().getMedia().get(0).getLink();
        if (avatar != null && !avatar.trim().isEmpty()) {
            Picasso.get().load(avatar).error(R.drawable.image_not_found).resize(150, 150).into(holder.binding.ivImage);
        } else
            Picasso.get().load(R.drawable.image_not_found).into(holder.binding.ivImage);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    protected class Holder extends RecyclerView.ViewHolder {
        final CardVendorProductItemBinding binding;

        Holder(CardVendorProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(VendorProductViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}

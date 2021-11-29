package hama.alsaygh.kw.delivery.adapters.vendorProductItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.databinding.CardUserProductItemBinding;
import hama.alsaygh.kw.delivery.databinding.CardVendorProductItemBinding;
import hama.alsaygh.kw.delivery.model.cart.CartItem;

public class UserProductAdapter extends RecyclerView.Adapter<UserProductAdapter.Holder> {

    private final List<CartItem> mValues;


    public UserProductAdapter(List<CartItem> active_order_array) {
        this.mValues = active_order_array;
    }

    @NonNull
    @Override
    public UserProductAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardUserProductItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_user_product_item, parent, false);
        return new UserProductAdapter.Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int index) {
        final int position = index;
        holder.bind(new VendorProductViewModel(holder.itemView.getContext(), mValues.get(position)));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    protected class Holder extends RecyclerView.ViewHolder {
        final CardUserProductItemBinding binding;

        Holder(CardUserProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(VendorProductViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}

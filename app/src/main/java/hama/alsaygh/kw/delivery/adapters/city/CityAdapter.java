package hama.alsaygh.kw.delivery.adapters.city;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import hama.alsaygh.kw.delivery.listener.OnCityListener;
import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.databinding.RowCityItemBinding;
import hama.alsaygh.kw.delivery.model.country.City;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<City> mValues;
    private final OnCityListener onClickRecycleViewListener;

    public CityAdapter(List<City> items, OnCityListener onClickRecycleViewListener) {
        mValues = items;
        this.onClickRecycleViewListener = onClickRecycleViewListener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowCityItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_city_item, parent, false);
        return new ViewItemHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int index) {

        final int position = index;
        ViewItemHolder viewHolder = (ViewItemHolder) holder;
        viewHolder.bind(new CityViewModel(viewHolder.itemView.getContext(), mValues.get(position)));
        viewHolder.itemView.setOnClickListener(v -> {
            if (viewHolder.binding.cbItem.isChecked())
                setUnSelection(position);
            else
                setSelection(position);
        });

        viewHolder.binding.cbItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setSelection(position);
            } else
                setUnSelection(position);
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private class ViewItemHolder extends RecyclerView.ViewHolder {
        final RowCityItemBinding binding;

        ViewItemHolder(RowCityItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CityViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }


    }

    public void setSelection(int position) {


        City category = mValues.get(position);
        category.setSelected(true);
        category.setAdded_to_delegate(true);

        mValues.remove(position);
        mValues.add(position, category);

        notifyDataSetChanged();

        if (onClickRecycleViewListener != null) {
            onClickRecycleViewListener.onCitySelect(mValues.get(position), position);
        }
    }

    public void setUnSelection(int position) {


        City category = mValues.get(position);
        category.setSelected(false);
        category.setAdded_to_delegate(false);

        mValues.remove(position);
        mValues.add(position, category);

        notifyDataSetChanged();

        if (onClickRecycleViewListener != null) {
            onClickRecycleViewListener.onCityUnSelect(mValues.get(position), position);
        }
    }

}

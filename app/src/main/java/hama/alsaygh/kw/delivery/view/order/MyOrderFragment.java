package hama.alsaygh.kw.delivery.view.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import hama.alsaygh.kw.delivery.adapters.pagerAdapter.OrderPagerAdapter;
import hama.alsaygh.kw.delivery.view.base.BaseFragment;
import hama.alsaygh.kw.delivery.databinding.MyOrderFragmentBinding;
import com.google.android.material.tabs.TabLayout;

public class MyOrderFragment extends BaseFragment {

    FragmentManager fragmentManager;
    MyOrderFragmentBinding binding;
    OrderViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  return inflater.inflate(R.layout.active_order, container, false);

        binding = MyOrderFragmentBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new OrderViewModel(requireContext());
        binding.setModel(model);
        fragmentManager = getChildFragmentManager();
        OrderPagerAdapter orderPagerAdapter = new OrderPagerAdapter(fragmentManager, binding.tabMyOrder.getTabCount(),requireContext());
        binding.viewPagerMyorder.setAdapter(orderPagerAdapter);
        binding.tabMyOrder.setupWithViewPager(binding.viewPagerMyorder);
        binding.viewPagerMyorder.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabMyOrder));


    }
}

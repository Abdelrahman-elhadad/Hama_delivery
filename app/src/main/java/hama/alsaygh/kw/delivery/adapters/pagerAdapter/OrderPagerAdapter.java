package hama.alsaygh.kw.delivery.adapters.pagerAdapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.utils.AppConstants;
import hama.alsaygh.kw.delivery.view.order.OrdersFragment;

public class OrderPagerAdapter extends FragmentStatePagerAdapter {
    private int number_of_Tab;
    private Context context;

    public OrderPagerAdapter(@NonNull FragmentManager fm, int number_of_Tab, Context context) {
        super(fm);
        this.number_of_Tab = number_of_Tab;
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return OrdersFragment.newInstance(AppConstants.ACTIVE);
            case 1:
                return OrdersFragment.newInstance(AppConstants.PENDING);
            case 2:
                return OrdersFragment.newInstance(AppConstants.REFUND);
            default:
                 return OrdersFragment.newInstance(AppConstants.ACTIVE);
        }
    }

    @Override
    public int getCount() {
        return number_of_Tab;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.Active_Orders);
            case 1:
                return   context.getString(R.string.Pending_Orders);
            case 2:
                return context.getString(R.string.Archived_Orders);
            default:
                return context.getString(R.string.Active_Orders);
        }
    }

//    public AdapterPagerMyOrder(@NonNull FragmentManager fm, int behavior, int number_of_Tab) {
//        super(fm, behavior);
//        this.number_of_Tab = number_of_Tab;
//    }
}

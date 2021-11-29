package hama.alsaygh.kw.delivery.adapters.pagerAdapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.view.order.orderDetails.userInfo.UserInformationFragment;
import hama.alsaygh.kw.delivery.view.order.orderDetails.vendorInfo.VendorInformationFragment;

public class OrderTrackingPagerAdapter extends FragmentStatePagerAdapter {
    private final int number_of_Tab;
    private final Order order;
    private final String status;
    private final Context context;


    public OrderTrackingPagerAdapter(Context context,@NonNull FragmentManager fm, int number_of_Tab, Order order, String status) {
        super(fm);
        this.number_of_Tab = number_of_Tab;
        this.order = order;
        this.status = status;
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return VendorInformationFragment.newInstance(order, status);
            case 1:
                return UserInformationFragment.newInstance(order,status);
            case 2:
                return UserInformationFragment.newInstance(order,status);

            default:
                return VendorInformationFragment.newInstance(order, status);

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
                return context.getString(R.string.Vendors_Information);
            case 1:
                return   context.getString(R.string.User_Information);
            case 2:
                return context.getString(R.string.User_Information);
            default:
                return context.getString(R.string.Vendors_Information);
        }
    }
}


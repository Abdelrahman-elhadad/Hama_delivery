package hama.alsaygh.kw.delivery.view.order.orderDetails.userInfo;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.utils.AppConstants;
import hama.alsaygh.kw.delivery.view.map.MapActivity;

public class UserInfoViewModel extends ViewModel {

    private final String TAG = "VendorViewModel";

    Context context;
    Order order;
    String status;

    public UserInfoViewModel(Context context, Order order, String status) {
        this.context = context;
        this.order = order;
        this.status = status;

    }

    public String getName()
    {
        return order.getUser_info().getName();
    }

    public String getEmail()
    {
        return order.getUser_info().getEmail();
    }
    public String getAddress() {
        return order.getUser_info().getAddress();
    }

    public String getPhone() {
        return order.getUser_info().getMobile() ;
    }

    public String getProductCount() {
        return context.getString(R.string.Products).replace("xx", order.getItems_count() + "");
    }

    public String getDeliveryFees() {
        return order.getDelivery_fees() + " " + order.getCurrency();
    }

    public String getTotalPrice() {
        return order.getTotal() + " " + order.getCurrency();
    }

    public void onAddressClick(View view)
    {
        Intent intent=new Intent(view.getContext(), MapActivity.class);
        intent.putExtra(AppConstants.ADDRESS,order.getDelivery().toString());
        view.getContext().startActivity(intent);
    }

}

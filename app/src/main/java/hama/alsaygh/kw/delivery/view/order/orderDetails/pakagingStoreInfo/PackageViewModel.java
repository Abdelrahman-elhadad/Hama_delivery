package hama.alsaygh.kw.delivery.view.order.orderDetails.pakagingStoreInfo;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.model.check.CheckResponse;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.repo.OrderRepo;
import hama.alsaygh.kw.delivery.utils.AppConstants;

public class PackageViewModel extends ViewModel {

    private final String TAG = "VendorViewModel";

    Context context;
    Order order;
    String status;
    OrderRepo orderRepo;
    private MutableLiveData<CheckResponse> checkResponseMutableLiveData;
    public PackageViewModel(Context context, Order order, String status) {
        this.context = context;
        this.order = order;
        this.status = status;
        orderRepo=new OrderRepo();
    }

    public  MutableLiveData<CheckResponse> getQrObserver()
    {
        if(checkResponseMutableLiveData==null)
            checkResponseMutableLiveData=new MutableLiveData<>();
        return checkResponseMutableLiveData;
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
    public int getScanVisibility()
    {
        return status.equalsIgnoreCase(AppConstants.ACTIVE)? View.VISIBLE:View.GONE;
    }

    public void getQr(int order_id,int store_id)
    {
        orderRepo.getOrderQr(context,order_id,store_id,checkResponseMutableLiveData);
    }
}

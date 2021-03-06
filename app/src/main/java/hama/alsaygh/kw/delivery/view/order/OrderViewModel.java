package hama.alsaygh.kw.delivery.view.order;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.model.check.CheckResponse;
import hama.alsaygh.kw.delivery.model.order.OrderResponse;
import hama.alsaygh.kw.delivery.model.order.OrdersResponse;
import hama.alsaygh.kw.delivery.model.qr.QRDeliveryResponse;
import hama.alsaygh.kw.delivery.repo.OrderRepo;
import hama.alsaygh.kw.delivery.utils.AppConstants;
import hama.alsaygh.kw.delivery.view.notification.NotificationsActivity;

public class OrderViewModel extends ViewModel {

    private final String TAG = "OrderViewModel";

    private MutableLiveData<OrdersResponse> ordersResponseMutableLiveData;
    private MutableLiveData<OrderResponse> orderResponseMutableLiveData;
    private MutableLiveData<QRDeliveryResponse> confirmDeliveredResponseMutableLiveData;

    String status;
    private OrderRepo orderRepo;
    Context context;

    public OrderViewModel(Context context, String status) {
        this.context = context;
        orderRepo=new OrderRepo();
        this.status = status;

    }

    public MutableLiveData<QRDeliveryResponse> getConfirmDeliveredObservable() {
        if (confirmDeliveredResponseMutableLiveData == null)
            confirmDeliveredResponseMutableLiveData = new MutableLiveData<>();

        return confirmDeliveredResponseMutableLiveData;
    }
    public MutableLiveData<OrdersResponse> getOrdersObservable() {
        if (ordersResponseMutableLiveData == null)
            ordersResponseMutableLiveData = new MutableLiveData<>();

        return ordersResponseMutableLiveData;
    }

    public MutableLiveData<OrderResponse> getOrderObservable() {
        if (orderResponseMutableLiveData == null)
            orderResponseMutableLiveData = new MutableLiveData<>();

        return orderResponseMutableLiveData;
    }

    public void getOrders(String status)
    {
        orderRepo.getOrders(context,status,ordersResponseMutableLiveData);
    }

    public void getOrder(int id,String status)
    {
        orderRepo.getOrder(context,id,status,orderResponseMutableLiveData);
    }

    public void onNotificationClick(View view)
    {

        view.getContext().startActivity(new Intent(view.getContext(), NotificationsActivity.class));
    }
    public int getScanVisibility()
    {
        return status.equalsIgnoreCase(AppConstants.ACTIVE)? View.VISIBLE:View.GONE;
    }

    public void postConfirmDelivered(int id)
    {
        orderRepo.postConfirmDelivered(context,id,confirmDeliveredResponseMutableLiveData);
    }


}

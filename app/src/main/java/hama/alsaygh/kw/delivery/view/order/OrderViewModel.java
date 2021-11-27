package hama.alsaygh.kw.delivery.view.order;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.model.order.OrdersResponse;
import hama.alsaygh.kw.delivery.repo.OrderRepo;

public class OrderViewModel extends ViewModel {

    private final String TAG = "OrderViewModel";

    private MutableLiveData<OrdersResponse> ordersResponseMutableLiveData;

    private OrderRepo orderRepo;
    Context context;

    public OrderViewModel(Context context) {
        this.context = context;
        orderRepo=new OrderRepo();

    }

    public MutableLiveData<OrdersResponse> getOrdersObservable() {
        if (ordersResponseMutableLiveData == null)
            ordersResponseMutableLiveData = new MutableLiveData<>();

        return ordersResponseMutableLiveData;
    }

    public void getOrders(String status)
    {
        orderRepo.getOrders(context,status,ordersResponseMutableLiveData);
    }
}

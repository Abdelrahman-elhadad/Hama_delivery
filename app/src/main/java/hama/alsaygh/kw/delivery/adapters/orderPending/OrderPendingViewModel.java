package hama.alsaygh.kw.delivery.adapters.orderPending;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.model.cart.CartItem;
import hama.alsaygh.kw.delivery.model.check.CheckResponse;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.model.product.Product;
import hama.alsaygh.kw.delivery.repo.OrderRepo;


public class OrderPendingViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final Order storeModel;
    private MutableLiveData<CheckResponse> acceptOrderMutableLiveData;
    private final OrderRepo orderRepo;

    public OrderPendingViewModel(Context context, Order storeModel) {
        this.context = context;
        this.storeModel = storeModel;
        orderRepo=new OrderRepo();
    }

    public MutableLiveData<CheckResponse> getAcceptOrderObserver()
    {
        if(acceptOrderMutableLiveData==null)
            acceptOrderMutableLiveData=new MutableLiveData<>();
        return acceptOrderMutableLiveData;
    }

    public void acceptOrder()
    {
        orderRepo.acceptOrder(context,storeModel.getId(),acceptOrderMutableLiveData);
    }

    public String getItemsNames() {
        String names = "";

        for (CartItem cartItem : storeModel.getItems()) {
            Product product = cartItem.getProduct();
            if (names.isEmpty())
                names = product.getName();
            else
                names = names + "," + product.getName();
        }

        return names;
    }

    public String getPrice() {
        return storeModel.getTotal() + " " + storeModel.getCurrency();
    }
}
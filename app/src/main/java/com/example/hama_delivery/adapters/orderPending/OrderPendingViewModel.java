package com.example.hama_delivery.adapters.orderPending;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hama_delivery.model.cart.CartItem;
import com.example.hama_delivery.model.check.CheckResponse;
import com.example.hama_delivery.model.order.Order;
import com.example.hama_delivery.model.product.Product;
import com.example.hama_delivery.repo.OrderRepo;


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
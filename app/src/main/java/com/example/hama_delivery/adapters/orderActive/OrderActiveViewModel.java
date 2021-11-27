package com.example.hama_delivery.adapters.orderActive;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.hama_delivery.model.cart.CartItem;
import com.example.hama_delivery.model.country.City;
import com.example.hama_delivery.model.order.Order;
import com.example.hama_delivery.model.product.Product;


public class OrderActiveViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Order storeModel;

    public OrderActiveViewModel(Context context, Order storeModel) {
        this.context = context;
        this.storeModel = storeModel;
    }

    public String getItemsNames()
    {
        String names="";

        for (CartItem cartItem: storeModel.getItems()) {
            Product product=cartItem.getProduct();
            if(names.isEmpty())
                names=product.getName();
            else
                names=names+","+product.getName();
        }

        return names;
    }

    public String getPrice()
    {
        return storeModel.getTotal()+" "+storeModel.getCurrency();
    }
}
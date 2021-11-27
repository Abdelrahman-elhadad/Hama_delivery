package hama.alsaygh.kw.delivery.adapters.orderReturn;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.model.cart.CartItem;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.model.product.Product;


public class OrderReturnViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Order storeModel;

    public OrderReturnViewModel(Context context, Order storeModel) {
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
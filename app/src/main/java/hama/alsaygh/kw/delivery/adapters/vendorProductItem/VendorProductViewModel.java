package hama.alsaygh.kw.delivery.adapters.vendorProductItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.model.cart.CartItem;
import hama.alsaygh.kw.delivery.model.store.Store;
import hama.alsaygh.kw.delivery.utils.AppConstants;


public class VendorProductViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    CartItem storeModel;

    public VendorProductViewModel(Context context, CartItem storeModel) {
        this.context = context;
        this.storeModel = storeModel;
    }

    public String getName()
    {

        return storeModel.getProduct().getName();
    }

    public String getPrice()
    {

        return storeModel.getTotal()+" ";
    }

    public boolean isAccepted()
    {

        return storeModel.getStatus().equalsIgnoreCase(AppConstants.ACCEPTED);
    }

    public int getVisibility()
    {

        return storeModel.getStatus().equalsIgnoreCase(AppConstants.ACCEPTED)? View.GONE:View.VISIBLE;
    }

}
package hama.alsaygh.kw.delivery.adapters.vendorInfo;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.model.cart.CartItem;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.model.product.Product;
import hama.alsaygh.kw.delivery.model.store.Store;


public class VendorInfoViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Store storeModel;

    public VendorInfoViewModel(Context context, Store storeModel) {
        this.context = context;
        this.storeModel = storeModel;
    }

    public String getName()
    {

        return storeModel.getStore_name();
    }

    public String getDate()
    {
        return storeModel.getDate();
    }
    public String getTime()
    {
        return storeModel.getTime();
    }
    public String getPhoneNumber()
    {
        return storeModel.getPhone_number();
    }
    public String getItemCount()
    {
        return context.getString(R.string.Products).replace("xx",storeModel.getItems_count()+"") ;
    }
    public String getAddress()
    {
        return storeModel.getAddress();
    }
}
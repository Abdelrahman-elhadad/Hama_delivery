package hama.alsaygh.kw.delivery.view.qr;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.model.check.CheckResponse;
import hama.alsaygh.kw.delivery.repo.OrderRepo;

public class QrViewModel extends ViewModel {

    private final String TAG = "OrderViewModel";

    private MutableLiveData<CheckResponse> confirmDeliveredResponseMutableLiveData;
    private MutableLiveData<CheckResponse> confirmReceiveResponseMutableLiveData;

    private OrderRepo orderRepo;
    Context context;

    public QrViewModel(Context context) {
        this.context = context;
        orderRepo=new OrderRepo();

    }

    public MutableLiveData<CheckResponse> getConfirmDeliveredObservable() {
        if (confirmDeliveredResponseMutableLiveData == null)
            confirmDeliveredResponseMutableLiveData = new MutableLiveData<>();

        return confirmDeliveredResponseMutableLiveData;
    }

    public MutableLiveData<CheckResponse> getConfirmOrderReceiveObservable() {
        if (confirmReceiveResponseMutableLiveData == null)
            confirmReceiveResponseMutableLiveData = new MutableLiveData<>();

        return confirmReceiveResponseMutableLiveData;
    }


    public void postConfirmReceived(int id,int vendor_id,String qr)
    {
        orderRepo.postConfirmReceived(context,id,vendor_id,qr,confirmReceiveResponseMutableLiveData);
    }

    public void postConfirmDelivered(int id,String qr)
    {
        orderRepo.postConfirmDelivered(context,id,qr,confirmReceiveResponseMutableLiveData);
    }
}

package hama.alsaygh.kw.delivery.view.map;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.model.notifications.NotificationsResponse;
import hama.alsaygh.kw.delivery.repo.ProfileRepo;

public class MapsViewModel extends ViewModel {

    private final String TAG = "MapsViewModel";

    Context context;
    private MutableLiveData<NotificationsResponse> notificationsResponseMutableLiveData;

    ProfileRepo profileRepo;
    public MapsViewModel(Context context) {
        this.context = context;
        profileRepo=new ProfileRepo();
    }

    public MutableLiveData<NotificationsResponse> getNotificationsObservable() {
        if (notificationsResponseMutableLiveData == null)
            notificationsResponseMutableLiveData = new MutableLiveData<>();

        return notificationsResponseMutableLiveData;
    }

    public void getNotification(int page)
    {
        profileRepo.getNotification(context,page,notificationsResponseMutableLiveData);
    }
}

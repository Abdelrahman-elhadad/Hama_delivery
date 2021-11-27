package hama.alsaygh.kw.delivery.adapters.city;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.model.country.City;


public class CityViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    City storeModel;

    public CityViewModel(Context context, City storeModel) {
        this.context = context;
        this.storeModel = storeModel;
    }

    public String getName() {
        return storeModel.getName();
    }

    public boolean isChecked() {
        return storeModel.isAdded_to_delegate();
    }

}
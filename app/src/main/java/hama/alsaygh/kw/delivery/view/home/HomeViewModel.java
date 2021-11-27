package hama.alsaygh.kw.delivery.view.home;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.listener.LoginListener;
import hama.alsaygh.kw.delivery.model.country.City;
import hama.alsaygh.kw.delivery.model.image.ImageResponse;
import hama.alsaygh.kw.delivery.model.user.LoginResponse;
import hama.alsaygh.kw.delivery.model.user.User;
import hama.alsaygh.kw.delivery.repo.AuthRepo;
import hama.alsaygh.kw.delivery.repo.ProfileRepo;
import hama.alsaygh.kw.delivery.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.delivery.view.auth.LoginActivity;
import hama.alsaygh.kw.delivery.view.profile.editProfile.EditProfileActivity;

import org.json.JSONArray;

public class HomeViewModel extends ViewModel {

    private final String TAG = "SplashActivityViewModel";

    Context context;

    public HomeViewModel(Context context) {
        this.context = context;
    }

}

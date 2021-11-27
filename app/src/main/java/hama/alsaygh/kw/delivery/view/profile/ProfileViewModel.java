package hama.alsaygh.kw.delivery.view.profile;

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

public class ProfileViewModel extends ViewModel {

    private final String TAG = "SplashActivityViewModel";

    private final AuthRepo authRepo;
    private final ProfileRepo profileRepo;

    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();

    private MutableLiveData<LoginResponse> loginResponseMutableLiveData;
    private MutableLiveData<ImageResponse> imageResponseMutableLiveData;

    private User user;
    Context context;
    LoginListener listener;

    public ProfileViewModel(Context context, LoginListener listener) {
        this.context = context;
        this.listener = listener;
        authRepo = new AuthRepo();
        profileRepo = new ProfileRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
        user = SharedPreferenceConstant.getSharedPreferenceUser(context);
    }

    public User getUser() {
        return user;
    }

    public MutableLiveData<LoginResponse> getLoginObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }

    public MutableLiveData<ImageResponse> getImageObservable() {
        if (imageResponseMutableLiveData == null)
            imageResponseMutableLiveData = new MutableLiveData<>();

        return imageResponseMutableLiveData;
    }

    public void uploadImage(String path) {
        profileRepo.uploadImage(context, path, imageResponseMutableLiveData);
    }

    public String getAddress() {
        return SharedPreferenceConstant.getSharedPreferenceUser(context).getAddress();
    }

    public String getBirthDate() {
        return SharedPreferenceConstant.getSharedPreferenceUser(context).getBirth_date();
    }

    public String getUserName() {
        return SharedPreferenceConstant.getSharedPreferenceUser(context).getName();
    }

    public String getSerialNumber() {
        return SharedPreferenceConstant.getSharedPreferenceUser(context).getSerial_number();
    }

    public float getRate() {
        return SharedPreferenceConstant.getSharedPreferenceUser(context).getRate();
    }

    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }

    public void onUpdateProfileClick(View view) {

        if (listener != null)
            listener.validation();
        if (user.isValid()) {
            loginVisibility.set(View.GONE);
            pbLoginVisibility.set(View.VISIBLE);

            profileRepo.updateProfile(context, user,putCityToJson(), loginResponseMutableLiveData);
        }
    }

    public void onLogOutClick(View view) {

        authRepo.logout(view.getContext(), null);
        SharedPreferenceConstant.clearSharedPreference(view.getContext());
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);

    }

    public void onEditProfileClick(View view) {
        Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
        view.getContext().startActivity(intent);


    }

    public TextWatcher passwordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user.setPassword(s.toString());
            }
        };
    }

    public TextWatcher passwordNewTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user.setNewPassword(s.toString());
            }
        };
    }

    public TextWatcher confirmPasswordNewTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user.setConfirmNewPassword(s.toString());
            }
        };
    }

    public TextWatcher addressTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user.setAddress(s.toString());
            }
        };
    }

    public TextWatcher userNameTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setName(s.toString());
            }
        };
    }

    public ObservableInt getLoginVisibility() {
        return loginVisibility;
    }

    public ObservableInt getPbLoginVisibility() {
        return pbLoginVisibility;
    }

    public String putCityToJson() {
        JSONArray jsonArray = new JSONArray();
        for (City city : user.getCities()) {

            jsonArray.put(city.getId());
        }
        return jsonArray.toString();
    }
}

package com.example.hama_delivery.view.auth;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hama_delivery.HomeActivity;
import com.example.hama_delivery.listener.LoginListener;
import com.example.hama_delivery.model.user.LoginResponse;
import com.example.hama_delivery.repo.AuthRepo;

public class LoginActivityViewModel extends ViewModel {

    private final String TAG = "SplashActivityViewModel";

    private final AuthRepo authRepo;
    private String userName, password;

    private final ObservableInt loginVisibility = new ObservableInt();
    private  final ObservableInt pbLoginVisibility = new ObservableInt();
    private final LoginListener listener;
    private  MutableLiveData<LoginResponse> loginResponseMutableLiveData;

    public LoginActivityViewModel(LoginListener listener) {
        this.listener = listener;
        authRepo=new AuthRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }

    public MutableLiveData<LoginResponse> getLoginObservable()
    {
        if(loginResponseMutableLiveData==null)
            loginResponseMutableLiveData=new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }

    public void login(Context context)
    {
        authRepo.Login(context,userName,password,loginResponseMutableLiveData);
    }

    public void onLoginClick(View view) {

        if (listener != null)
            listener.validation();
        if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
            setLoginVisibility(View.GONE);
            setPbLoginVisibility(View.VISIBLE);

            login(view.getContext());
        }
    }

    public void onForgetPasswordClick(View view) {
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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
                password = s.toString();
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
                userName = s.toString();
            }
        };
    }

    public ObservableInt getLoginVisibility() {
        return loginVisibility;
    }

    public ObservableInt getPbLoginVisibility() {
        return pbLoginVisibility;
    }
}

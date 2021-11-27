package hama.alsaygh.kw.delivery.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import hama.alsaygh.kw.delivery.model.check.CheckResponse;
import hama.alsaygh.kw.delivery.model.user.LoginResponse;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;


public class AuthRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// login ///////////////////////////////
    public void Login(final Context context, final String serial_number, final String password,  final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "auth/login";

                FormBody body = new FormBody.Builder()
                        .add("password", password)
                        .add("serial_number", serial_number)
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:auth/login " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, LoginResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new LoginResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final LoginResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }


    //////////////////////// logout /////////////////////////
    public void logout(final Context context, final MutableLiveData<CheckResponse> signUpResponseMutableLiveData) {

        new Thread(() -> {
            CheckResponse signUpResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "auth/logout";

                FormBody body = new FormBody.Builder().build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();
                Log.i(TAG, "Response:auth/logout " + responseString);


                signUpResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, CheckResponse.class);

            } catch (Exception e) {
                e.printStackTrace();
                signUpResponse = new CheckResponse();
                signUpResponse.setStatus(false);
                signUpResponse.setMessage("server error");

            }
            if (signUpResponseMutableLiveData != null) {
                final CheckResponse finalLoginSocialResponse = signUpResponse;
                new Handler(Looper.getMainLooper()).post(() -> signUpResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }
        }).start();
    }


}

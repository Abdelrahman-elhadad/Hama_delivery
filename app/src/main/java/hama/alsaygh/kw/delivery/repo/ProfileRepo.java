package hama.alsaygh.kw.delivery.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import hama.alsaygh.kw.delivery.model.image.ImageResponse;
import hama.alsaygh.kw.delivery.model.user.LoginResponse;
import hama.alsaygh.kw.delivery.model.user.User;
import hama.alsaygh.kw.delivery.utils.Utils;

import java.io.File;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ProfileRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// Profile ///////////////////////////////
    public void getProfile(final Context context,  final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "profileData";
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:profileData " + responseString);

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
    public void getUser(final Context context,  final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "fetch-user";
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:fetch-user " + responseString);

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
    public void updateProfile(final Context context, final User user,final String cites, final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "updateProfile";
                FormBody body = new FormBody.Builder()
                        .add("name",user.getName())
                        .add("birth_date",user.getBirth_date())
                        .add("address",user.getAddress())
                        .add("avatar",user.getAvatar())
                        .add("cities",cites)
                        .add("current_password",user.getPassword().isEmpty()?"":user.getPassword())
                        .add("password",user.getPassword().isEmpty()?"":user.getNewPassword())
                        .add("password_confirmation",user.getPassword().isEmpty()?"":user.getConfirmNewPassword())
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).put(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:updateProfile " + responseString);
responseString=responseString.replace("\"data\":[]","\"data\":{}");
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
    public void updatePassword(final Context context,final String password,final String newPassword,  final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "updateProfile";
                FormBody body = new FormBody.Builder()
                        .add("current_password",password)
                        .add("password",newPassword)
                        .add("password_confirmation",newPassword)
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).put(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:updateProfile " + responseString);

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

    public void uploadImage(final Context context,final String image,  final MutableLiveData<ImageResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            ImageResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "image-upload";
                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM);
                String IMG_Name = Utils.getInstance().encodeString(image.substring(image.lastIndexOf("/") + 1));
                builder.addFormDataPart("image", IMG_Name, RequestBody.create(RequestWrapper.getInstance().getMEDIA_TYPE(), new File(image)));
                MultipartBody requestBody = builder.build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeaderMedia(context);
                Request request = requestBuilder.url(url).post(requestBody).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:image-upload " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, ImageResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new ImageResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final ImageResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }
}

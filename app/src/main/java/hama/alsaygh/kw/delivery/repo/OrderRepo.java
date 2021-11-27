package hama.alsaygh.kw.delivery.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import hama.alsaygh.kw.delivery.model.check.CheckResponse;
import hama.alsaygh.kw.delivery.model.image.ImageResponse;
import hama.alsaygh.kw.delivery.model.order.OrdersResponse;
import hama.alsaygh.kw.delivery.model.user.LoginResponse;
import hama.alsaygh.kw.delivery.utils.Utils;

import java.io.File;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OrderRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// order ///////////////////////////////
    public void getOrders(final Context context,final String status,  final MutableLiveData<OrdersResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            OrdersResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "order/index/"+status;
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG,  "order/index/"+status+" : " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, OrdersResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new OrdersResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final OrdersResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }
    public void acceptOrder(final Context context, final int order_id, final MutableLiveData<CheckResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            CheckResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "order/"+order_id+"/accept";
                FormBody body = new FormBody.Builder().build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:order/"+order_id+"/accept " + responseString);
                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, CheckResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new CheckResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final CheckResponse finalLoginSocialResponse = loginSocialResponse;
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

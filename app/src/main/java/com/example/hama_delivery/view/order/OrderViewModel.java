package com.example.hama_delivery.view.order;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hama_delivery.listener.LoginListener;
import com.example.hama_delivery.model.country.City;
import com.example.hama_delivery.model.image.ImageResponse;
import com.example.hama_delivery.model.order.OrdersResponse;
import com.example.hama_delivery.model.user.LoginResponse;
import com.example.hama_delivery.model.user.User;
import com.example.hama_delivery.repo.AuthRepo;
import com.example.hama_delivery.repo.OrderRepo;
import com.example.hama_delivery.repo.ProfileRepo;
import com.example.hama_delivery.utils.SharedPreferenceConstant;
import com.example.hama_delivery.view.auth.LoginActivity;
import com.example.hama_delivery.view.profile.editProfile.EditProfileActivity;

import org.json.JSONArray;

public class OrderViewModel extends ViewModel {

    private final String TAG = "OrderViewModel";

    private MutableLiveData<OrdersResponse> ordersResponseMutableLiveData;

    private OrderRepo orderRepo;
    Context context;

    public OrderViewModel(Context context) {
        this.context = context;
        orderRepo=new OrderRepo();

    }

    public MutableLiveData<OrdersResponse> getOrdersObservable() {
        if (ordersResponseMutableLiveData == null)
            ordersResponseMutableLiveData = new MutableLiveData<>();

        return ordersResponseMutableLiveData;
    }

    public void getOrders(String status)
    {
        orderRepo.getOrders(context,status,ordersResponseMutableLiveData);
    }
}

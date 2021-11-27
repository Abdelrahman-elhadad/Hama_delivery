package com.example.hama_delivery;

import com.example.hama_delivery.model.order.Order;

public interface OrderListener {
    void onOrderClick(Order order, int position);

}

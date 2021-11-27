package hama.alsaygh.kw.delivery.listener;

import hama.alsaygh.kw.delivery.model.order.Order;

public interface OrderListener {
    void onOrderClick(Order order, int position);

}

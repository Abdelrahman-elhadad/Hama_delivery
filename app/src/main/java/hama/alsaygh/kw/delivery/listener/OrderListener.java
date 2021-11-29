package hama.alsaygh.kw.delivery.listener;

import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.model.store.Store;

public interface OrderListener {
    void onOrderClick(Order order, int position);
    void onOrderQrReceivedClick(Store store, int position);
    void refresh();

}

package hama.alsaygh.kw.delivery.model.cart;

import hama.alsaygh.kw.delivery.model.product.Product;
import hama.alsaygh.kw.delivery.model.store.Store;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartItem implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("product")
    private Product product;

    @SerializedName("status")
    private String status;

    @SerializedName("status_trans")
    private String status_trans;

    @SerializedName("packaging_product")
    private Store packaging_product;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("total")
    private double total;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        if (product == null)
            product = new Product();
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_trans() {
        return status_trans;
    }

    public void setStatus_trans(String status_trans) {
        this.status_trans = status_trans;
    }

    public Store getPackaging_product() {
        return packaging_product;
    }

    public void setPackaging_product(Store packaging_product) {
        this.packaging_product = packaging_product;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

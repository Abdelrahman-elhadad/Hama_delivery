package hama.alsaygh.kw.delivery.model.qr;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import hama.alsaygh.kw.delivery.model.user.User;

public class QRDelivery implements Serializable {

    @SerializedName("path")
    private String path;



    public QRDelivery() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

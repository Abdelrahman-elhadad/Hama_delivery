package com.example.hama_delivery.store;

public class canceled_order_array {
    int img;
    String img_name;

    public canceled_order_array(int img, String img_name) {
        this.img = img;
        this.img_name = img_name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }
}

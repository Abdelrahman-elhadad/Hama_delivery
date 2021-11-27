package hama.alsaygh.kw.delivery.model.user;

import hama.alsaygh.kw.delivery.model.country.City;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName(value = "name", alternate = "full_name")
    private String name;

    @SerializedName("serial_number")
    private String serial_number;

    @SerializedName("birth_date")
    private String birth_date;

    @SerializedName("address")
    private String address;

    @SerializedName("rate")
    private float rate;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("email")
    private String email;

    @SerializedName("orders")
    private List<Object> orders;

    @SerializedName("cities")
    private List<City> cities;


    private String password, newPassword, confirmNewPassword;

    public User() {
    }

    public String getPassword() {
        if(password==null)
            password="";
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        if(newPassword==null)
            newPassword="";
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        if(confirmNewPassword==null)
            confirmNewPassword="";
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        if (address == null)
            address = "";
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        if (name == null)
            name = "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getBirth_date() {
        if (birth_date == null)
            birth_date = "";
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public List<Object> getOrders() {
        return orders;
    }

    public void setOrders(List<Object> orders) {
        this.orders = orders;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public boolean isValid() {
        boolean isValid = true;
        if (!getPassword().isEmpty()) {
            if (getNewPassword().isEmpty())
                isValid = false;
            else {
                if (getConfirmNewPassword().isEmpty())
                    isValid = false;
                else if (!getConfirmNewPassword().equals(getNewPassword()))
                    isValid = false;
            }
        }

        return !getName().isEmpty() && !getBirth_date().isEmpty() && !getAddress().isEmpty() && isValid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", serial_number='" + serial_number + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", address='" + address + '\'' +
                ", rate=" + rate +
                ", orders=" + orders +
                ", cities=" + cities +
                '}';
    }
}

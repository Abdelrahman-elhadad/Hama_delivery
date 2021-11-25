package com.example.hama_delivery;

import com.example.hama_delivery.model.country.City;

public interface OnCityListener {
    void onCitySelect(City city, int position);
    void onCityUnSelect(City city, int position);

}

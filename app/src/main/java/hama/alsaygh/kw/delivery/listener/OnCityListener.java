package hama.alsaygh.kw.delivery.listener;

import hama.alsaygh.kw.delivery.model.country.City;

public interface OnCityListener {
    void onCitySelect(City city, int position);
    void onCityUnSelect(City city, int position);

}

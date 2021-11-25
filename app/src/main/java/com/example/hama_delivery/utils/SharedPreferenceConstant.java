package com.example.hama_delivery.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hama_delivery.model.user.User;
import com.example.hama_delivery.repo.RequestWrapper;

public class SharedPreferenceConstant {

    private static final String SHARED_PREFERENCE_USER_ACCOUNT = "USER_ACCOUNT";
    private static final String KEY_USER_ACCOUNT_TOKEN = "Token";
    private static final String KEY_USER_ACCOUNT_LOGIN_USER = "UserLogin";


    public static void setSharedPreferenceUser(Context context, User user) {

        if (context != null) {
            try {
                String token = RequestWrapper.getInstance().getGson().toJson(user);
                SharedPreferences preferences = context.getSharedPreferences(SharedPreferenceConstant.SHARED_PREFERENCE_USER_ACCOUNT, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(KEY_USER_ACCOUNT_LOGIN_USER, token);
                editor.apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static User getSharedPreferenceUser(Context context) {

        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(SharedPreferenceConstant
                    .SHARED_PREFERENCE_USER_ACCOUNT, MODE_PRIVATE);

            return RequestWrapper.getInstance().getGson().fromJson(preferences.getString(KEY_USER_ACCOUNT_LOGIN_USER, ""), User.class);
        }
        return null;
    }

    public static void setSharedPreferenceUserToken(Context context, String token) {

        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(SharedPreferenceConstant
                    .SHARED_PREFERENCE_USER_ACCOUNT, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_USER_ACCOUNT_TOKEN, token);
            editor.apply();
        }
    }


    public static String getSharedPreferenceUserToken(Context context) {

        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(SharedPreferenceConstant
                    .SHARED_PREFERENCE_USER_ACCOUNT, MODE_PRIVATE);

            return preferences.getString(KEY_USER_ACCOUNT_TOKEN, "");
        }
        return "";
    }


    public static void clearSharedPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SharedPreferenceConstant.SHARED_PREFERENCE_USER_ACCOUNT,
                MODE_PRIVATE);
        preferences.edit().clear().apply();
    }


}

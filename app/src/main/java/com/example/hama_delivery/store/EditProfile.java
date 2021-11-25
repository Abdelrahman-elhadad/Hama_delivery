package com.example.hama_delivery.store;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hama_delivery.BaseActivity;
import com.example.hama_delivery.R;

public class EditProfile extends BaseActivity {
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));
        setContentView(R.layout.edit_profile);
    }
}

package com.example.hama_delivery;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hama_delivery.base.BaseActivity;
import com.example.hama_delivery.fragment.OrderTracking;
import com.example.hama_delivery.view.order.MyOrderFragment;
import com.example.hama_delivery.view.profile.MyProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Home11";
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    public final int Home = 0;
    public final int MyProfile = 1;
    public final int OrderTrack = 2;

    public static int position;
    // private  MyOrder myOrder;


    // public static int position = HomeActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));
        setContentView(R.layout.home);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.buttom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // bottomNavigationView.setItemIconTintList(null);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        fragmentManager = getSupportFragmentManager();
       bottomNavigationView.setSelectedItemId(R.id.it_home);

    }

    private void commitFragment(Fragment fragment, int position) {
        HomeActivity.position = position;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.liner1, fragment);
        fragmentTransaction.commit();
    }

    public void openOrderTrack() {
        commitFragment(new OrderTracking(), OrderTrack);
    }

    @Override
    public void onBackPressed() {
        if (position != Home)
            commitFragment(new MyOrderFragment(), Home);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.it_home) {
            commitFragment(new MyOrderFragment(), Home);
            Log.e(TAG, "home1" + menuItem.getItemId());

            return true;
        }
        if (menuItem.getItemId() == R.id.item_profile) {
            commitFragment(new MyProfileFragment(), MyProfile);
            Log.e(TAG, "my profile" + menuItem.getItemId());
            return true;
        }
        return false;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

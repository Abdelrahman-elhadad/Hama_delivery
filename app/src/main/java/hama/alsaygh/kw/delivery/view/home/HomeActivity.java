package hama.alsaygh.kw.delivery.view.home;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.view.base.BaseActivity;
import hama.alsaygh.kw.delivery.databinding.ActivityHomeBinding;
import hama.alsaygh.kw.delivery.view.order.MyOrderFragment;
import hama.alsaygh.kw.delivery.view.profile.MyProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";
    private   FragmentManager fragmentManager;

    public final int Home = 0;
    public final int MyProfile = 1;

    public static int position;
   ActivityHomeBinding binding;
    HomeViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= ActivityHomeBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
        model=new HomeViewModel(this);
        binding.setModel(model);
        binding.buttomNav.setOnNavigationItemSelectedListener(this);
        // bottomNavigationView.setItemIconTintList(null);

        fragmentManager = getSupportFragmentManager();
        binding.buttomNav.setSelectedItemId(R.id.it_home);

    }

    private void commitFragment(Fragment fragment, int position) {
        HomeActivity.position = position;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_replace, fragment);
        fragmentTransaction.commit();
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

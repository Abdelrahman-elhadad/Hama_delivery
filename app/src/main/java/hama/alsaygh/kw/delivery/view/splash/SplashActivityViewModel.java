package hama.alsaygh.kw.delivery.view.splash;

import android.content.Intent;
import android.util.Log;
import android.widget.SeekBar;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.delivery.view.home.HomeActivity;
import hama.alsaygh.kw.delivery.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.delivery.view.auth.LoginActivity;

public class SplashActivityViewModel extends ViewModel {

    private final String TAG = "SplashActivityViewModel";

    public SplashActivityViewModel() {
    }

    public void onValueChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.d(TAG, "onProgressChanged:" + progress);
        if (progress == 100) {
            if(SharedPreferenceConstant.getSharedPreferenceUserToken(seekBar.getContext()).isEmpty()) {
                Intent intent = new Intent(seekBar.getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                seekBar.getContext().startActivity(intent);
            }else {
                Intent intent = new Intent(seekBar.getContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                seekBar.getContext().startActivity(intent);
            }
        }
    }
}

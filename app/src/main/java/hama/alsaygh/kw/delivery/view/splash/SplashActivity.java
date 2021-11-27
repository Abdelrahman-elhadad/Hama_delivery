package hama.alsaygh.kw.delivery.view.splash;

import android.animation.ValueAnimator;
import android.os.Bundle;

import hama.alsaygh.kw.delivery.base.BaseActivity;
import hama.alsaygh.kw.delivery.databinding.ActivityMainBinding;

public class SplashActivity extends BaseActivity {


    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       SplashActivityViewModel model=new SplashActivityViewModel();
       binding.setModel(model);

        final ValueAnimator anim = ValueAnimator.ofInt(0, binding.seekBar.getMax());
        anim.setDuration(1500);
        anim.addUpdateListener(animation -> {
            int animProgress = (Integer) animation.getAnimatedValue();
            binding.seekBar.setProgress(animProgress);
        });
        anim.start();


    }
}
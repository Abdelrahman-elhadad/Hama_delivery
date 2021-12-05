package hama.alsaygh.kw.delivery.view.qr;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmadrosid.svgloader.SvgLoader;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.databinding.ActivityQrDeliveryBinding;
import hama.alsaygh.kw.delivery.utils.LocalUtils;

public class QRDeliveryActivity extends AppCompatActivity {

    ActivityQrDeliveryBinding binding;
    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColorDark();
        if (getSupportActionBar() != null)
            getSupportActionBar().hide(); //<< this
        LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));

        binding = ActivityQrDeliveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {
            path = getIntent().getStringExtra("path");
        }

        //  GlideToVectorYou.justLoadImage(this, Uri.parse(path), binding.ivQr);

        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher_hama, R.mipmap.ic_launcher_hama)
                .load(path, binding.ivQr);


    }

    private void setStatusBarColorDark() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#00181E"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SvgLoader.pluck().close();
    }
}
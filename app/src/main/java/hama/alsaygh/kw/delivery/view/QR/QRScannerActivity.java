package hama.alsaygh.kw.delivery.view.QR;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.databinding.ActivityQrscannerBinding;
import hama.alsaygh.kw.delivery.dialog.LoginDialog;
import hama.alsaygh.kw.delivery.utils.AppConstants;
import hama.alsaygh.kw.delivery.utils.LocalUtils;

public class QRScannerActivity extends AppCompatActivity {

    ActivityQrscannerBinding binding;
    QrViewModel qrViewModel;
    int order_id;
    int vendor_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColorDark();
        if (getSupportActionBar() != null)
            getSupportActionBar().hide(); //<< this
        LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));

        binding = ActivityQrscannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        qrViewModel = new QrViewModel(this);
        binding.setModel(qrViewModel);

        if (getIntent() != null) {
            order_id = getIntent().getIntExtra(AppConstants.ORDER_ID, -1);
            vendor_id = getIntent().getIntExtra(AppConstants.VENDOR_ID, -1);
        }

        if(vendor_id!=-1)
        {
            binding.tvScan.setText(getString(R.string.sscan));
        }

        CodeScanner mCodeScanner = new CodeScanner(this, binding.scannerView);
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
            if (vendor_id == -1)
                qrViewModel.postConfirmDelivered(order_id, result.getText());
            else
                qrViewModel.postConfirmReceived(order_id, vendor_id, result.getText());
        }));

        mCodeScanner.startPreview();

        qrViewModel.getConfirmOrderReceiveObservable().observe(this, checkResponse -> {
            if (checkResponse.isStatus()) {
                Snackbar.make(binding.llScanToReceive, checkResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            } else {
                if (checkResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog loginDialog = LoginDialog.newInstance();
                    loginDialog.show(getSupportFragmentManager(), "login");
                } else
                    Snackbar.make(binding.llScanToReceive, checkResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });
        qrViewModel.getConfirmDeliveredObservable().observe(this, checkResponse -> {
            if (checkResponse.isStatus()) {
                Snackbar.make(binding.llScanToReceive, checkResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            } else {
                if (checkResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog loginDialog = LoginDialog.newInstance();
                    loginDialog.show(getSupportFragmentManager(), "login");
                } else
                    Snackbar.make(binding.llScanToReceive, checkResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    private void setStatusBarColorDark() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#00181E"));
    }
}
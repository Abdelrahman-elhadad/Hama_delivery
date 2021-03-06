package hama.alsaygh.kw.delivery.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hama.alsaygh.kw.delivery.view.home.HomeActivity;
import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.view.base.BaseActivity;
import hama.alsaygh.kw.delivery.databinding.ActivityLoginBinding;
import hama.alsaygh.kw.delivery.listener.LoginListener;
import hama.alsaygh.kw.delivery.utils.SharedPreferenceConstant;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends BaseActivity implements LoginListener {
    ActivityLoginBinding binding;
    LoginActivityViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new LoginActivityViewModel(this);
        binding.setModel(model);

        model.getLoginObservable().observe(this, loginResponse -> {

            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if (loginResponse.isStatus()) {
                SharedPreferenceConstant.setSharedPreferenceUserToken(LoginActivity.this,loginResponse.getData().getToken());
                SharedPreferenceConstant.setSharedPreferenceUser(LoginActivity.this,loginResponse.getData().getDelegate());

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else
                Snackbar.make(binding.editText, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public void validation() {

        if (model.getPassword() == null || model.getPassword().isEmpty())
            binding.editPassword.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.editPassword.setBackgroundResource(R.drawable.back_edit_txt);

        if (model.getUserName() == null || model.getUserName().isEmpty())
            binding.editText.setBackgroundResource(R.drawable.back_edit_txt_red);
        else
            binding.editText.setBackgroundResource(R.drawable.back_edit_txt);


    }
}


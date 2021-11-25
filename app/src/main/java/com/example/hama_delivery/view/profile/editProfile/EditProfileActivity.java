package com.example.hama_delivery.view.profile.editProfile;

import static android.os.Build.VERSION_CODES.M;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.hama_delivery.OnCityListener;
import com.example.hama_delivery.R;
import com.example.hama_delivery.adapters.city.CityAdapter;
import com.example.hama_delivery.base.BaseActivity;
import com.example.hama_delivery.databinding.ActivityEditProfileBinding;
import com.example.hama_delivery.dialog.LoginDialog;
import com.example.hama_delivery.model.country.City;
import com.example.hama_delivery.utils.CheckAndRequestPermission;
import com.example.hama_delivery.utils.FileUtils;
import com.example.hama_delivery.utils.SharedPreferenceConstant;
import com.example.hama_delivery.utils.Utils;
import com.example.hama_delivery.utils.image.CircleTransform;
import com.example.hama_delivery.view.profile.ProfileViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditProfileActivity extends BaseActivity implements OnCityListener {

    ActivityEditProfileBinding binding;
    ProfileViewModel model;
    List<City>selectedCity=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ProfileViewModel(this, () -> {
            if (model.getUser().getName().isEmpty()) {
                binding.etName.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else {
                binding.etName.setBackgroundResource(R.drawable.back_edit_txt);
            }

            if (model.getUser().getAddress().isEmpty()) {
                binding.etAddress.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else {
                binding.etAddress.setBackgroundResource(R.drawable.back_edit_txt);
            }

            if (model.getUser().getBirth_date().isEmpty()) {
                binding.tvDate.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else {
                binding.tvDate.setBackgroundResource(R.drawable.back_edit_txt);
            }

            if (!model.getUser().getPassword().isEmpty()) {

                if (model.getUser().getConfirmNewPassword().isEmpty()) {
                    binding.etConfirmPassword.setBackgroundResource(R.drawable.back_edit_txt_red);
                } else {
                    binding.etConfirmPassword.setBackgroundResource(R.drawable.back_edit_txt);
                }

                if (model.getUser().getNewPassword().isEmpty()) {
                    binding.etCurrentPassword.setBackgroundResource(R.drawable.back_edit_txt_red);
                } else
                    binding.etCurrentPassword.setBackgroundResource(R.drawable.back_edit_txt);

                if (!model.getUser().getNewPassword().isEmpty() && !model.getUser().getConfirmNewPassword().isEmpty() && !model.getUser().getConfirmNewPassword().equals(model.getUser().getNewPassword())) {
                    binding.etConfirmPassword.setBackgroundResource(R.drawable.back_edit_txt_red);

                } else {
                    binding.etConfirmPassword.setBackgroundResource(R.drawable.back_edit_txt);
                }

            }

        });
        binding.setModel(model);

        binding.imageViewBack.setOnClickListener(v -> {
            onBackPressed();
        });

        String avatar = SharedPreferenceConstant.getSharedPreferenceUser(this).getAvatar();
        if (avatar != null && !avatar.trim().isEmpty()) {
            Picasso.get().load(avatar).placeholder(R.drawable.ic_default_user).error(R.drawable.ic_default_user).transform(new CircleTransform()).into(binding.ivProfile);
        } else
            Picasso.get().load(R.drawable.ic_default_user).placeholder(R.drawable.ic_default_user).error(R.drawable.ic_default_user).into(binding.ivProfile);

        final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                binding.tvDate.setText(date);
            }
        };
        binding.tvDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();

            Date date = calendar.getTime();
            try {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                date = sdf.parse(binding.tvDate.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            int year = date.getYear() + 1900;
            int month = date.getMonth();
            int day = date.getDate();

            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Holo_Dialog_MinWidth
                    , mDateSetListener, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });


        binding.rlProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionGallery();
            }
        });

        model.getImageObservable().observe(this, imageResponse -> {
            if (imageResponse.isStatus()) {
                model.getUser().setAvatar(imageResponse.getData().getFile_name());
            }
        });

        for(City city :SharedPreferenceConstant.getSharedPreferenceUser(this).getCities()) {
            if(city.isAdded_to_delegate())
            selectedCity.add(city);
        }
        model.getUser().setCities(selectedCity);
        CityAdapter cityAdapter=new CityAdapter(SharedPreferenceConstant.getSharedPreferenceUser(this).getCities(),this);
        binding.rvCities.setLayoutManager(new GridLayoutManager(this,3));
        binding.rvCities.setAdapter(cityAdapter);

        model.getLoginObservable().observe(this,loginResponse -> {
            model.setLoginVisibility(View.VISIBLE);
            model.setPbLoginVisibility(View.GONE);
            if(loginResponse.isStatus())
            {
                Snackbar.make(binding.btnUpdate,loginResponse.getMessage(),Snackbar.LENGTH_SHORT).show();
            }else
            {
                if (loginResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog loginDialog = LoginDialog.newInstance();
                    loginDialog.show(getSupportFragmentManager(), "login");
                } else
                Snackbar.make(binding.btnUpdate,loginResponse.getMessage(),Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void permissionGallery() {
        if (Build.VERSION.SDK_INT >= M) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!CheckAndRequestPermission.hasPermissions(this, permissions)) {
                CheckAndRequestPermission.requestStorage(this);
            } else {
                openGallery();
            }
        } else {
            openGallery();
        }

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Utils.PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utils.PICK_IMAGE) {
            if (resultCode == RESULT_OK) {

                final String profilePath = FileUtils.getPath(EditProfileActivity.this, data.getData());
                Picasso.get().load(data.getData()).transform(new CircleTransform()).into(binding.ivProfile);

                model.uploadImage(profilePath);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && !CheckAndRequestPermission.isFoundPermissionDenied(grantResults)) {
            openGallery();
        }
    }

    @Override
    public void onCitySelect(City city, int position) {

        selectedCity.add(city);
        model.getUser().setCities(selectedCity);
    }

    @Override
    public void onCityUnSelect(City city, int position) {

        for (int i = 0; i <selectedCity.size() ; i++) {
            if(selectedCity.get(i).getId()==city.getId())
            {
                selectedCity.remove(i);
                break;
            }
        }
        model.getUser().setCities(selectedCity);
    }


}
package hama.alsaygh.kw.delivery.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.InputFilter;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.repo.AuthRepo;
import hama.alsaygh.kw.delivery.view.auth.LoginActivity;
import com.faltenreich.skeletonlayout.Skeleton;

import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class Utils {


    @SuppressLint("StaticFieldLeak")
    private static Utils utils;
    private Context context;
    public static final int PICK_IMAGE = 1;
    public static final int PICK_IMAGE_ID = 2;

    private SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private SimpleDateFormat sdfDateTimeDelivery = new SimpleDateFormat("EEEE dd MMM hh:mm a", Locale.US);

    private String sort_key = "";
    private int category_level_1 = -1;
    private int category_level_2 = -1;
    private int category_level_3 = -1;
    private String range_price_from = "";
    private String range_price_to = "";
    private String type_of_price = "";

    enum SearchType {
        stores, categories, packaging_stores, products
    }

    public void setSort_key(String sort_key) {
        this.sort_key = sort_key;
    }

    public String getSort_key() {
        return sort_key;
    }

    public int getCategory_level_1() {
        return category_level_1;
    }

    public void setCategory_level_1(int category_level_1) {
        this.category_level_1 = category_level_1;
    }

    public int getCategory_level_2() {
        return category_level_2;
    }

    public void setCategory_level_2(int category_level_2) {
        this.category_level_2 = category_level_2;
    }

    public int getCategory_level_3() {
        return category_level_3;
    }

    public void setCategory_level_3(int category_level_3) {
        this.category_level_3 = category_level_3;
    }

    public String getRange_price_from() {
        return range_price_from;
    }

    public void setRange_price_from(String range_price_from) {
        this.range_price_from = range_price_from;
    }

    public String getRange_price_to() {
        return range_price_to;
    }

    public void setRange_price_to(String range_price_to) {
        this.range_price_to = range_price_to;
    }

    public String getType_of_price() {
        return type_of_price;
    }

    public void setType_of_price(String type_of_price) {
        this.type_of_price = type_of_price;
    }

    private Utils() {

    }

    public static Utils getInstance() {
        if (utils == null)
            utils = new Utils();
        return utils;
    }


    public  String getUUID(Context context) {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif: all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b: macBytes) {
                    //res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }

                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public SimpleDateFormat getSdfDateTimeDelivery() {
        return sdfDateTimeDelivery;
    }

    public SimpleDateFormat getSdfDate() {
        return sdfDate;
    }

    public boolean isValidEmail(CharSequence target) {
        return (target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

//    public void hideMyKeyboard(View v) {
//        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context
//                .INPUT_METHOD_SERVICE);
//        if (inputMethodManager != null && inputMethodManager.isActive()) {
//            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        }
//    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    //    @SuppressWarnings("unused")
    public String encodeString(String attValueID) {
        try {
            return URLEncoder.encode(attValueID, "UTF-8");

        } catch (Exception e) {
            return "";
        }
    }

//    public String decodeString(String attValueID) {
//        try {
//            return URLDecoder.decode(attValueID, "UTF-8");
//        } catch (Exception e) {
//            return "";
//        }
//    }


    private void setEditTextMaxLength(int length, AppCompatEditText edtPhoneNumber) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        edtPhoneNumber.setFilters(filterArray);
    }


    public void logOut(final FragmentActivity context) {
        if (context != null)
            try {

                new AuthRepo().logout(context,null);
                SharedPreferenceConstant.clearSharedPreference(context);

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        context.finish();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public int getScreenWidth(AppCompatActivity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int getScreenHeight(AppCompatActivity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

//    public float convertDpToPx(float dp) {
//        return dp * context.getResources().getDisplayMetrics().density;
//    }

    public int convertDpToPxInt(float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public String getDurationTime(Calendar calendar) {

        LocalUtils.getInstance().updateResources(Utils.getInstance().getContext(), LocalUtils.getInstance().getLanguageShort(Utils.getInstance().getContext()));
        SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd,yyyy", Locale.getDefault());
        return sdfDate.format(calendar.getTime());

    }

    public void setSkeletonMaskAndShimmer(Context context, Skeleton skeleton) {
        skeleton.setShimmerColor(ContextCompat.getColor(context, R.color.color_light_shimmer));
        skeleton.setMaskColor(ContextCompat.getColor(context, R.color.color_light_mask));
        skeleton.setShimmerDurationInMillis(1000);
    }

    public void setSkeletonMaskAndShimmerDark(Context context, Skeleton skeleton) {
//        skeleton.setShimmerColor(ContextCompat.getColor(context, R.color.color_Dark_shimmer));
//        skeleton.setMaskColor(ContextCompat.getColor(context, R.color.color_Dark_mask));
        skeleton.setShimmerDurationInMillis(1000);
    }

}


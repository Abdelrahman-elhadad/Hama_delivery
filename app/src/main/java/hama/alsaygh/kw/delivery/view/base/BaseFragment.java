package hama.alsaygh.kw.delivery.view.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hama.alsaygh.kw.delivery.utils.LocalUtils;

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LocalUtils.getInstance().updateResources(getContext(), LocalUtils.getInstance().getLanguageShort(getContext()));
//        if (SharedPreferenceConstant.getSharedPreferenceDarkMode(getContext())) {
//            this.getActivity().setTheme(R.style.darktheme);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//
//        } else {
//            this.getActivity().setTheme(R.style.AppTheme);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            setLightStatusBar();
//        }
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide(); //<< this
//
//    }
//
//    private void setStatusBarColorDark() {
//        Window window = getActivity().getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.sign_in));
//
//
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalUtils.getInstance().updateResources(requireContext(), LocalUtils.getInstance().getLanguageShort(requireContext()));

    }

    private void setLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = getActivity().getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);
        }
    }
}

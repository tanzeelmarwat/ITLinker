package com.tanzeelmarwat.itlinker.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.google.common.hash.Hashing;
import com.tanzeelmarwat.itlinker.R;
import com.tanzeelmarwat.itlinker.models.UserType;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Utility {

    public static String FONT_AWESOME = "FontAwesome.ttf";
    public static SVProgressHUD progressHUD;

    public static Drawable getDrawableFromId(Context mContext, int id) {
        return ContextCompat.getDrawable(mContext, id);
    }

    public static Typeface applyFontAwesome(Context mContext) {
        return Typeface.createFromAsset(mContext.getAssets(), Utility.FONT_AWESOME);
    }

    public static boolean isConnectedToInternet(Context mContext) {
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public static void hideInputKeyboard(Context mContext, View view) {
        InputMethodManager inputKeyboard = (InputMethodManager) mContext.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputKeyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String encodePassword(String password) {
        String encodedPassword = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            encodedPassword = Hashing.sha256()
                    .hashString(password, StandardCharsets.UTF_8)
                    .toString();
        } else {
            encodedPassword = Hashing.sha256()
                    .hashString(password, Charset.forName("UTF-8"))
                    .toString();
        }

        return encodedPassword;
    }

    public static void showProgress(Context mContext) {
        dismissProgress();
        progressHUD =  new SVProgressHUD(mContext);


        progressHUD.getProgressBar().setCircleColor(R.color.colorPrimary);
        progressHUD.getProgressBar().setCircleProgressColor(R.color.colorPrimary);
        progressHUD.getProgressBar().setMinimumHeight(100);
        progressHUD.getProgressBar().setMinimumWidth(100);


        progressHUD.showWithStatus(Constants.PROGRESS_TEXT, SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    public static void dismissProgress() {
        if (progressHUD == null || !progressHUD.isShowing()) {
            return;
        }
        progressHUD.dismiss();
    }

    public static boolean isShowingProgress() {
        return progressHUD != null && progressHUD.isShowing();
    }

    public static boolean validateName(Context mContext, String name, TextInputLayout layout) {
        if(name != null && !name.equalsIgnoreCase("")) {
            layout.setErrorEnabled(false);
            return true;
        } else {
            layout.setError(mContext.getString(R.string.error_empty));
            return false;
        }
    }

    public static boolean validateEmail(Context mContext, String email, TextInputLayout layout) {
        if(email != null && !email.equalsIgnoreCase("")) {
            layout.setErrorEnabled(false);
            return true;
        } else {
            layout.setError(mContext.getString(R.string.error_empty));
            return false;
        }
    }

    public static boolean validateContact(Context mContext, String email, TextInputLayout layout) {
        if(email != null && !email.equalsIgnoreCase("")) {
            layout.setErrorEnabled(false);
            return true;
        } else {
            layout.setError(mContext.getString(R.string.error_empty));
            return false;
        }
    }

    public static boolean validatePassword(Context mContext, String password, TextInputLayout layout) {
        if(password != null && !password.equalsIgnoreCase("")) {
            layout.setErrorEnabled(false);
            return true;
        } else {
            layout.setError(mContext.getString(R.string.error_empty));
            return false;
        }
    }

    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return Math.round(px);
    }

    public static String getUserTypeId(HashMap<Integer, Object> spinnerMap, String name) {
        if(spinnerMap != null) {
            for (int i = 0; i < spinnerMap.size(); i++) {
                if (spinnerMap.get(i) instanceof UserType) {
                    UserType userType = (UserType) spinnerMap.get(i);
                    if (userType.getName().equalsIgnoreCase(name)) {
                        return userType.getId();
                    }
                }
            }
        }
        return "0";
    }
}

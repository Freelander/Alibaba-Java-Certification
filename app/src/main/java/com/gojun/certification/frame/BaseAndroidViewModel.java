package com.gojun.certification.frame;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;

import com.gojun.certification.core.SessionData;
import com.gojun.certification.core.ThemeCore;

/**
 * Description:
 *
 * @author Jun 11/29/20
 */
public abstract class BaseAndroidViewModel extends AndroidViewModel {

    private String mTag;
    private String mTitle;
    private final ObservableInt mThemeColor = new ObservableInt(-1);


    public BaseAndroidViewModel(@NonNull Application application) {
        super(application);
    }

    public String getTag() {
        if (mTag == null) {
            mTag = this.getClass().getSimpleName() + "_" + this.hashCode();
        }

        return mTag;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public final void init(Activity activity, @StringRes int titleResId) {
        mThemeColor.set((int) SessionData.getObject(activity, ThemeCore.THEME_COLOR, ThemeCore.THEME_DEF_COLOR));
        if (titleResId != -1) {
            mTitle = activity.getString(titleResId);
        }
    }

    public ObservableInt getThemeColor() {
        return mThemeColor;
    }

    public int getThemeColor(Activity activity) {
        return (int) SessionData.getObject(activity, ThemeCore.THEME_COLOR, ThemeCore.THEME_DEF_COLOR);
    }

    public void setThemeColor(int themeColor) {
        mThemeColor.set(themeColor);
    }

    public String getTitle() {
        if (TextUtils.isEmpty(mTitle)) {
            return "";
        }
        return mTitle;
    }

    public void showLongToast(String msg) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String msg) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }
}

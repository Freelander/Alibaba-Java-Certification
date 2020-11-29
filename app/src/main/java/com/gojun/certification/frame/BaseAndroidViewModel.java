package com.gojun.certification.frame;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gojun.certification.core.SessionData;
import com.gojun.certification.core.ThemeCore;

/**
 * Description:
 *
 * @author Jun 11/29/20
 */
public abstract class BaseAndroidViewModel extends AndroidViewModel {

    private String tag;

    public BaseAndroidViewModel(@NonNull Application application) {
        super(application);
    }

    public String getTag() {
        if (tag == null) {
            tag = this.getClass().getSimpleName() + "_" + this.hashCode();
        }

        return tag;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public int getThemeColor(Context context) {
        return (int) SessionData.getObject(context, ThemeCore.THEME_COLOR, ThemeCore.THEME_DEF_COLOR);
    }

    public void showLongToast(String msg) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String msg) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

    //Fragment或者Activity的onStart调用，用于触发数据拉取场景等
    public void onStart() {

    }
}

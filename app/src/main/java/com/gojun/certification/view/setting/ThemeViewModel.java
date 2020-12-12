package com.gojun.certification.view.setting;

import android.app.Application;
import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;

import com.gojun.certification.frame.BaseAndroidViewModel;
import com.gojun.certification.frame.SingleLiveEvent;

/**
 * Description:
 *
 * @author Jun 12/12/20
 */
public class ThemeViewModel extends BaseAndroidViewModel {

    private final SingleLiveEvent<Integer> mChangeThemeColorEvent = new SingleLiveEvent<>();

    private final ObservableInt mSbRebValue = new ObservableInt(200);
    private final ObservableInt mSbGreenValue = new ObservableInt(100);
    private final ObservableInt mSbBlueValue = new ObservableInt(150);

    @ColorInt
    int[] colors={0xFFFD4831,0xFFE62565,0xFF9B2FAE,0xFF673FB4,0xFF4054B2,0xFF2A96ED,
            0xFF1EA9F0,0xFF1FBBD0,0xFF159588,0xFF50AE55,0xFFCCD949,0xFFFEE94E,
            0xFFFDC02F,0xFFFA9527,0xFFF9572F,0xFF775448,0xFF9C9C9C,0xFF5E7985};

    public ThemeViewModel(@NonNull Application application) {
        super(application);
    }

    public void initColorValue(int themeColor) {
        mSbRebValue.set((themeColor & 0x00ff0000) >> 16);
        mSbGreenValue.set((themeColor & 0x0000ff00) >> 8);
        mSbBlueValue.set((themeColor & 0x000000ff));
    }

    public ObservableInt getSbRebValue() {
        return mSbRebValue;
    }

    public ObservableInt getSbGreenValue() {
        return mSbGreenValue;
    }

    public ObservableInt getSbBlueValue() {
        return mSbBlueValue;
    }

    public SingleLiveEvent<Integer> getChangeThemeColorEvent() {
        return mChangeThemeColorEvent;
    }

    public void sendChangeThemeColorEvent() {
        int themeColor= Color.argb(255, mSbRebValue.get(), mSbGreenValue.get(), mSbBlueValue.get());
        getThemeColor().set(themeColor);
        mChangeThemeColorEvent.setValue(themeColor);
    }
}

package com.gojun.certification.view;

import android.app.Application;

import androidx.annotation.NonNull;

import com.gojun.certification.frame.BaseAndroidViewModel;
import com.gojun.certification.frame.SingleLiveEvent;

/**
 * Description:
 *
 * @author Jun 12/8/20
 */
public class MainViewModel extends BaseAndroidViewModel {

    private final SingleLiveEvent<Boolean> mChangeThemeEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Integer> mChangeBottomNavEvent = new SingleLiveEvent<>();

    private int mCurrentItemId = -1;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public SingleLiveEvent<Boolean> getChangeThemeEvent() {
        return mChangeThemeEvent;
    }

    public SingleLiveEvent<Integer> getChangeBottomNavEvent() {
        return mChangeBottomNavEvent;
    }

    public void sendChangeThemeEvent() {
        mChangeThemeEvent.setValue(true);
    }

    public void sendChangeBottomNavEvent(int viewId) {
        mChangeBottomNavEvent.setValue(viewId);
    }

    public void setCurrentItemId(int itemId) {
        this.mCurrentItemId = itemId;
    }

    public int getCurrentItemId() {
        return mCurrentItemId;
    }
}

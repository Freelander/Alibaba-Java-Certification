package com.gojun.certification.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gojun.certification.R;
import com.gojun.certification.frame.SingleLiveEvent;
import com.gojun.certification.utils.IntentHelper;
import com.gojun.certification.view.setting.AboutAppAct;

/**
 * Description:
 *
 * @author Jun 11/29/20
 */

public class SettingViewModel extends MainViewModel {

    private final SingleLiveEvent<Boolean> mClickThemeColorEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> mClickHighSettingEvent = new SingleLiveEvent<>();

    public SettingViewModel(@NonNull Application application) {
        super(application);
        setCurrentItemId(R.id.tab_setting);
    }

    public SingleLiveEvent<Boolean> getClickThemeColorEvent() {
        return mClickThemeColorEvent;
    }

    public SingleLiveEvent<Boolean> getClickHighSettingEvent() {
        return mClickHighSettingEvent;
    }

    public void clickAbout(Fragment fragment) {
        IntentHelper.openClass(fragment.requireContext(), AboutAppAct.class);
    }

    public void clickCustomThemeColor(Fragment fragment) {
        mClickThemeColorEvent.setValue(true);
    }

    public void clickHighSetting(Fragment fragment) {
        mClickHighSettingEvent.setValue(true);
    }
}

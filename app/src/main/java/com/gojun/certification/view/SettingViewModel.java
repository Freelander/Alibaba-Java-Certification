package com.gojun.certification.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gojun.certification.frame.BaseAndroidViewModel;
import com.gojun.certification.frame.SingleLiveEvent;
import com.gojun.certification.utils.IntentHelper;
import com.gojun.certification.view.setting.AboutAppAct;
import com.gojun.certification.view.setting.HighSetAct;

/**
 * Description:
 *
 * @author Jun 11/29/20
 */

public class SettingViewModel extends BaseAndroidViewModel {

    private final SingleLiveEvent<Boolean> mClickThemeColorEvent = new SingleLiveEvent<>();

    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public SingleLiveEvent<Boolean> getClickThemeColorEvent() {
        return mClickThemeColorEvent;
    }

    public void clickAbout(Fragment fragment) {
        IntentHelper.openClass(fragment.requireContext(), AboutAppAct.class);
    }

    public void clickCustomThemeColor(Fragment fragment) {
        mClickThemeColorEvent.setValue(true);
    }

    public void clickHighSetting(Fragment fragment) {
        IntentHelper.openClass(fragment.requireContext(), HighSetAct.class);
    }
}

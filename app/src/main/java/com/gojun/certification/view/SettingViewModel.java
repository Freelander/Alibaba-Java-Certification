package com.gojun.certification.view;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gojun.certification.frame.BaseAndroidViewModel;
import com.gojun.certification.utils.IntentHelper;
import com.gojun.certification.view.setting.AboutAppAct;
import com.gojun.certification.view.setting.HighSetAct;
import com.gojun.certification.view.setting.ThemeAct;

/**
 * Description:
 *
 * @author Jun 11/29/20
 */

public class SettingViewModel extends BaseAndroidViewModel {

    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public void clickAbout(Fragment fragment) {
        IntentHelper.openClass(fragment.requireContext(), AboutAppAct.class);
    }

    public void clickCustomThemeColor(Fragment fragment) {
        fragment.startActivityForResult(
                new Intent(fragment.requireContext(), ThemeAct.class),
                ThemeAct.REQ_THEME);
    }

    public void clickHighSetting(Fragment fragment) {
        IntentHelper.openClass(fragment.requireContext(), HighSetAct.class);
    }
}

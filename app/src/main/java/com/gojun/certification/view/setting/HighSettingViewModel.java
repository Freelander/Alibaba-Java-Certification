package com.gojun.certification.view.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;

import com.gojun.certification.core.SessionData;
import com.gojun.certification.frame.BaseAndroidViewModel;
import com.gojun.certification.global.Constant;

/**
 * Description:
 *
 * @author Jun 12/13/20
 */
public class HighSettingViewModel extends BaseAndroidViewModel {

    private final ObservableInt mFailedCount = new ObservableInt(2);

    public HighSettingViewModel(@NonNull Application application) {
        super(application);
        int failCount = (Integer) SessionData.getObject(application, Constant.SP_REMOVE_COUNT, 2);
        mFailedCount.set(failCount);
    }

    public ObservableInt getFailedCount() {
        return mFailedCount;
    }

    public int getFailedCountValue() {
        return mFailedCount.get();
    }
}

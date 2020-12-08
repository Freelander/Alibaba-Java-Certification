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


    private final SingleLiveEvent<Boolean> mClickStudyEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> mClickAuthEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> mClickFailTopicEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> mClickSettingEvent = new SingleLiveEvent<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public SingleLiveEvent<Boolean> getClickStudyEvent() {
        return mClickStudyEvent;
    }

    public SingleLiveEvent<Boolean> getClickAuthEvent() {
        return mClickAuthEvent;
    }

    public SingleLiveEvent<Boolean> getClickFailTopicEvent() {
        return mClickFailTopicEvent;
    }

    public SingleLiveEvent<Boolean> getClickSettingEvent() {
        return mClickSettingEvent;
    }

    public void sendClickStudyEvent() {
        mClickStudyEvent.setValue(true);
    }

    public void sendClickAuthEvent() {
        mClickAuthEvent.setValue(true);
    }

    public void sendClickFailEvent() {
        mClickFailTopicEvent.setValue(true);
    }

    public void sendClickSettingEvent() {
        mClickSettingEvent.setValue(true);
    }
}

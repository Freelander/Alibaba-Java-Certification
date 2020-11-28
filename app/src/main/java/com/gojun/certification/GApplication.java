package com.gojun.certification;

import android.app.Application;

import com.gojun.certification.core.DataManager;
import com.gojun.certification.utils.LogCat;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Porster on 17/2/28.
 */

public class GApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        boolean debug=BuildConfig.DEBUG;
        LogCat.setDebug(debug);
        MobclickAgent.setDebugMode(debug);
        DataManager.getInstance().init(this);

    }
}

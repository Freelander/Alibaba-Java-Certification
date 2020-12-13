package com.gojun.certification.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gojun.certification.R;
import com.gojun.certification.utils.IntentHelper;
import com.gojun.certification.view.gift.GiftAct;

/**
 * Description:
 *
 * @author Jun 12/3/20
 */
public class SimulationAuthViewModel extends MainViewModel {

    public SimulationAuthViewModel(@NonNull Application application) {
        super(application);
        setCurrentItemId(R.id.tab_auth);
    }

    public void clickShowHistoryGrade() {
        showShortToast("没有该功能");
    }

    public void clickStartSimulationAuth(Fragment fragment) {
        IntentHelper.openClass(fragment.requireContext(), GiftAct.class);
    }
}

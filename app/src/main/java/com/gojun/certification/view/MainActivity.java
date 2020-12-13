package com.gojun.certification.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.gojun.certification.core.BaseActivity;
import com.gojun.certification.core.ThemeCore;
import com.gojun.certification.databinding.ActivityMainBinding;
import com.gojun.certification.utils.ApiUtils;

/**
 * Description:
 *
 * @author Jun 12/8/20
 */
public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;

    private MainViewModel mViewModel;
    private StudyViewModel mStudyViewModel;
    private SimulationAuthViewModel mAuthViewModel;
    private FailTopicViewModel mFailTopicViewModel;
    private SettingViewModel mSettingViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mStudyViewModel = new ViewModelProvider(this).get(StudyViewModel.class);
        mAuthViewModel = new ViewModelProvider(this).get(SimulationAuthViewModel.class);
        mFailTopicViewModel = new ViewModelProvider(this).get(FailTopicViewModel.class);
        mSettingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        mViewModel.init(this, -1);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mBinding.setViewModel(mViewModel);
        setContentView(mBinding.getRoot());

        tintTheme();
        setupObserve();
    }

    private void setupObserve() {
        mViewModel.getChangeThemeEvent().observe(this, aBoolean -> {
            if (aBoolean) {
                tintTheme();
            }
        });
    }

    private void tintTheme() {
        int themeColor = mViewModel.getThemeColor(this);
        mStudyViewModel.setThemeColor(themeColor);
        mAuthViewModel.setThemeColor(themeColor);
        mFailTopicViewModel.setThemeColor(themeColor);
        mSettingViewModel.setThemeColor(themeColor);

        if (ApiUtils.isLolinpop()) {
            getWindow().setStatusBarColor(ThemeCore.getStateBarColor(themeColor));
        }
    }
}

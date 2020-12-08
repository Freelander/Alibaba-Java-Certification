package com.gojun.certification.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.gojun.certification.R;
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
    private NavController mNavController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.init(this, -1);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mBinding.setViewModel(mViewModel);
        setContentView(mBinding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        mNavController = navHostFragment.getNavController();

        tintTheme();
        setupObserve();
    }

    private void setupObserve() {
        mViewModel.getClickStudyEvent().observe(this, aBoolean -> {
            if (aBoolean && !mNavController.popBackStack(R.id.study_page, false)) {
                mNavController.navigate(R.id.study_page);
            }
        });

        mViewModel.getClickAuthEvent().observe(this, aBoolean -> {
            if (aBoolean && !mNavController.popBackStack(R.id.auth_page, false)) {
                mNavController.navigate(R.id.auth_page);
            }
        });

        mViewModel.getClickFailTopicEvent().observe(this, aBoolean -> {
            if (aBoolean && !mNavController.popBackStack(R.id.fail_page, false)) {
                mNavController.navigate(R.id.fail_page);
            }
        });

        mViewModel.getClickSettingEvent().observe(this, aBoolean -> {
            if (aBoolean && !mNavController.popBackStack(R.id.setting_page, false)) {
                mNavController.navigate(R.id.setting_page);
            }
        });

    }

    private void tintTheme(){
        int themeColor= mViewModel.getThemeColor();
        if (ApiUtils.isLolinpop()) {
            getWindow().setStatusBarColor(ThemeCore.getStateBarColor(themeColor));
        }
        mBinding.llBottomNav.setBackgroundColor(themeColor);
    }
}
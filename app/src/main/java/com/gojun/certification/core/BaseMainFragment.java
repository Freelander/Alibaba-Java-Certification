package com.gojun.certification.core;

import android.annotation.SuppressLint;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.gojun.certification.R;
import com.gojun.certification.view.MainViewModel;

/**
 * Description:
 *
 * @author Jun 12/13/20
 */
public abstract class BaseMainFragment extends BaseFragment {
    
    public abstract MainViewModel getViewModel();
    
    @SuppressLint("NonConstantResourceId")
    protected void setupBottomNavObservers() {
        getViewModel().getChangeBottomNavEvent().observe(getViewLifecycleOwner(), tabId -> {
            switch (tabId) {
                case R.id.tab_study:
                    if (!getNavController().popBackStack(R.id.study_page, false)) {
                        getNavController().navigate(R.id.study_page);
                    }
                    break;
                case R.id.tab_auth:
                    if (!getNavController().popBackStack(R.id.auth_page, false)) {
                        getNavController().navigate(R.id.auth_page);
                    }
                    break;
                case R.id.tab_failed:
                    if (!getNavController().popBackStack(R.id.fail_page, false)) {
                        getNavController().navigate(R.id.fail_page);
                    }
                    break;
                case R.id.tab_setting:
                    if (!getNavController().popBackStack(R.id.setting_page, false)) {
                        getNavController().navigate(R.id.setting_page);
                    }
                    break;
            }
        });
    }
    
    protected NavController getNavController() {
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        return navHostFragment.getNavController();
    }
}

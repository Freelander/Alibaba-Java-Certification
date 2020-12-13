package com.gojun.certification.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.gojun.certification.R;
import com.gojun.certification.core.BaseMainFragment;
import com.gojun.certification.databinding.FragmentSettingBinding;
import com.gojun.certification.global.Constant;

import org.jetbrains.annotations.NotNull;

public class SettingFragment extends BaseMainFragment {

    private FragmentSettingBinding mBinding;
    private SettingViewModel mViewModel;
    private MainViewModel mMainViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(SettingViewModel.class);
        mViewModel.init(requireActivity(), R.string.setting);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mBinding = FragmentSettingBinding.inflate(inflater);
        mBinding.setViewModel(mViewModel);
        mBinding.setFragment(this);

        setupObserve();
        setupBottomNavObservers();
        return mBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_THEME && resultCode == Activity.RESULT_OK) {
            mMainViewModel.sendChangeThemeEvent();
        }
    }

    private void setupObserve() {
        mViewModel.getClickThemeColorEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                NavHostFragment.findNavController(this).navigate(R.id.action_setting_page_to_theme_page);
            }
        });
    }

    @Override
    public MainViewModel getViewModel() {
        return mViewModel;
    }
}

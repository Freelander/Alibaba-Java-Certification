package com.gojun.certification.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.gojun.certification.R;
import com.gojun.certification.core.BaseFragment;
import com.gojun.certification.databinding.FragmentSettingBinding;
import com.gojun.certification.view.setting.ThemeAct;

import org.jetbrains.annotations.NotNull;

public class SettingFragment extends BaseFragment {

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

        return mBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ThemeAct.REQ_THEME && resultCode == Activity.RESULT_OK) {
            mMainViewModel.sendChangeThemeEvent();
        }
    }
}

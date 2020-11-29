package com.gojun.certification.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.gojun.certification.R;
import com.gojun.certification.core.BaseFragment;
import com.gojun.certification.databinding.FragmentSettingBinding;

import org.jetbrains.annotations.NotNull;

public class SettingFragment extends BaseFragment {

    private FragmentSettingBinding mBinding;
    private SettingViewModel mViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentSettingBinding.inflate(inflater);
        mViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setFragment(this);
        mainView = mBinding.getRoot();
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.llTitle.titleBarTitle.setText(getString(R.string.setting));
        mBinding.lightingView.startLighting();

        int themeColor = mViewModel.getThemeColor(requireContext());
        mBinding.lightingView.setColor(themeColor);
        mBinding.llTitle.titleBarLayout.setBackgroundColor(themeColor);
    }
}

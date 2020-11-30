package com.gojun.certification.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        mViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        mViewModel.init(requireActivity(), R.string.setting);

        mBinding = FragmentSettingBinding.inflate(inflater);
        mBinding.setViewModel(mViewModel);
        mBinding.setFragment(this);

        return mBinding.getRoot();
    }
}

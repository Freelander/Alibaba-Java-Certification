package com.gojun.certification.view.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.gojun.certification.R;
import com.gojun.certification.core.BaseFragment;
import com.gojun.certification.core.ThemeCore;
import com.gojun.certification.databinding.FragmentThemeBinding;

/**
 * Description:
 *
 * @author Jun 12/12/20
 */
public class ThemeFragment extends BaseFragment {

    private FragmentThemeBinding mBinding;
    private ThemeViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ThemeViewModel.class);
        mViewModel.init(requireActivity(), R.string.theme_color);
        mViewModel.setReturnIconResId(R.drawable.back);
        mViewModel.initColorValue(ThemeCore.getThemeColor(requireActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentThemeBinding.inflate(inflater);
        mBinding.setViewModel(mViewModel);
        mBinding.setRebValue(mViewModel.getSbRebValue().get());
        mBinding.setGreenValue(mViewModel.getSbGreenValue().get());
        mBinding.setBlueValue(mViewModel.getSbBlueValue().get());

        setupObservers();
        return mBinding.getRoot();
    }

    private void setupObservers() {
        mViewModel.getClickStartButtonEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                NavHostFragment.findNavController(this).popBackStack();
            }
        });

        mViewModel.getChangeThemeColorEvent().observe(getViewLifecycleOwner(), themeColor -> {
            if (themeColor != null) {
                ThemeCore.saveThemeColor(requireActivity(), themeColor);
            }
        });
    }
}

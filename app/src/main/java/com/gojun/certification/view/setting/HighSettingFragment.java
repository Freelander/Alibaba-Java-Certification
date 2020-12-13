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
import com.gojun.certification.core.SessionData;
import com.gojun.certification.databinding.FragmentHighSettingBinding;
import com.gojun.certification.global.Constant;

/**
 * Description:
 *
 * @author Jun 12/13/20
 */
public class HighSettingFragment extends BaseFragment {

    private HighSettingViewModel mViewModel;
    private FragmentHighSettingBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HighSettingViewModel.class);
        mViewModel.init(requireActivity(), R.string.high_setting);
        mViewModel.setReturnIconResId(R.drawable.back);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentHighSettingBinding.inflate(inflater);
        mBinding.setViewModel(mViewModel);
        mBinding.setProgress(mViewModel.getFailedCountValue());

        setupObservers();
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        int value = mViewModel.getFailedCountValue();
        SessionData.setObject(mContext, Constant.SP_REMOVE_COUNT, value);
        super.onDestroyView();
    }

    private void setupObservers() {
        mViewModel.getClickStartButtonEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                NavHostFragment.findNavController(this).popBackStack();
            }
        });
    }
}

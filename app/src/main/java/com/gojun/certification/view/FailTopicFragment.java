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
import com.gojun.certification.databinding.FragmentFailedBinding;

public class FailTopicFragment extends BaseFragment {

    private FailTopicViewModel mViewModel;
    private FragmentFailedBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(FailTopicViewModel.class);
        mViewModel.init(requireActivity(), R.string.fail_topic);
        mViewModel.loadData(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentFailedBinding.inflate(inflater);
        mBinding.setFragment(this);
        mBinding.setViewModel(mViewModel);

        setupObservers();
        return mBinding.getRoot();
    }

    private void setupObservers() {
        mViewModel.getListResource().observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0) {
                mViewModel.getEmptyLayoutVisibility().set(false);
                mViewModel.getFailTopicText().add(0, "剩余" + list.size() + "道");
            } else {
                mViewModel.getEmptyText().add(0, "少侠一道题都没有做错呢");
                mViewModel.getEmptyLayoutVisibility().set(true);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mViewModel.loadData(requireContext());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            mViewModel.loadData(requireContext());
        }
    }
}

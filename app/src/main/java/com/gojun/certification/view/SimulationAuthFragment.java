package com.gojun.certification.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.gojun.certification.R;
import com.gojun.certification.core.BaseFragment;
import com.gojun.certification.databinding.FragmentSimulationAuthBinding;

public class SimulationAuthFragment extends BaseFragment {

    private SimulationAuthViewModel mViewModel;
    private FragmentSimulationAuthBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(SimulationAuthViewModel.class);
        mViewModel.init(requireActivity(), R.string.simulation_auth);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentSimulationAuthBinding.inflate(inflater);
        mBinding.setViewModel(mViewModel);
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        onHiddenChanged(false);
    }
}

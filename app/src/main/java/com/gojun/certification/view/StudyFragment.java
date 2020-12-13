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
import com.gojun.certification.core.BaseMainFragment;
import com.gojun.certification.core.DataManager;
import com.gojun.certification.core.SessionData;
import com.gojun.certification.databinding.FragmentStudyBinding;
import com.gojun.certification.global.Constant;
import com.gojun.certification.model.QuestionModel;
import com.gojun.certification.utils.AppConstants;
import com.gojun.certification.utils.IntentHelper;
import com.gojun.certification.view.study.StudyAct;
import com.gojun.certification.widget.XDialog;

import java.util.ArrayList;

public class StudyFragment extends BaseMainFragment {

    private StudyViewModel mViewModel;
    private FragmentStudyBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(StudyViewModel.class);
        mViewModel.init(requireActivity(), R.string.study);
        mViewModel.loadData(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentStudyBinding.inflate(inflater);
        mBinding.setViewModel(mViewModel);
        mBinding.setFragment(this);

        setupObservers();
        setupBottomNavObservers();
        return mBinding.getRoot();
    }

    private void setupObservers() {
        mViewModel.getClickDeleteEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                XDialog.showSelectDialog(mContext, "是否清空做题记录", new XDialog.DialogClickListener() {
                    @Override
                    public void confirm() {
                        DataManager.getInstance().saveListAsync(mContext, Constant.CACHE_HISTORY_STUDY, new ArrayList<QuestionModel>());
                        SessionData.setObject(mContext, Constant.SP_STUDY_LAST_CURRENT, 0);
                        mViewModel.getStartTipText().add(0, "");
                        mViewModel.getDeleteVisible().set(false);
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        });

        mViewModel.getClickStartEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                Bundle k = new Bundle();
                IntentHelper.openClassResult(mContext, StudyAct.class, AppConstants.REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            int current = (int) SessionData.getObject(mContext, Constant.SP_STUDY_LAST_CURRENT, 0);
            mViewModel.getStartTipText().add(0, current == 0 ? "开始练习" : "上次做到第" + current + "题");
            mViewModel.getDeleteVisible().set(current > 0);
        }
    }

    @Override
    public MainViewModel getViewModel() {
        return mViewModel;
    }
}

package com.gojun.certification.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gojun.certification.R;
import com.gojun.certification.core.BaseFragment;
import com.gojun.certification.utils.IntentHelper;
import com.gojun.certification.view.gift.GiftAct;

/**
 * 科举
 * Created by Porster on 2017/7/28.
 */

public class AuthFragment extends BaseFragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        mainView=inflater.inflate(R.layout.fragment_gift,container,false);
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    private void initUI() {
        addActionBar("模拟认证").getLeft().setVisibility(View.GONE);
        $(R.id.g_start,this);
        $(R.id.g_history,this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.g_start://开始模拟测试
                IntentHelper.openClass(mContext, GiftAct.class);
                break;
            case R.id.g_history://科举历史
                mActivity.showToast("没有该功能");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onHiddenChanged(false);
    }
}

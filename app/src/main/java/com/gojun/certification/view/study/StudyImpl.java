package com.gojun.certification.view.study;

import android.content.Context;
import android.os.Bundle;

import com.gojun.certification.core.BaseFragment;
import com.gojun.certification.core.SessionData;
import com.gojun.certification.global.Constant;
import com.gojun.certification.model.QuestionModel;
import com.gojun.certification.utils.IntentHelper;

import java.util.ArrayList;

/**
 * Created by Porster on 17/3/31.
 */

public class StudyImpl implements StudyM{

    private StudyP mStudyP;

    public StudyImpl(StudyP mStudyP){
        this.mStudyP=mStudyP;
    }

    @Override
    public ArrayList<BaseFragment> createQuestions(ArrayList<QuestionModel> giftModels) {
        ArrayList<BaseFragment> mFragments=new ArrayList<>();
        for (int i = 0; i < giftModels.size(); i++) {
            QuestionModel model=giftModels.get(i);
            QuestionFragment mQuestionFragment=new QuestionFragment();
            Bundle b=new Bundle();
            b.putSerializable(IntentHelper.KEY1,model);
            mQuestionFragment.setArguments(b);
            mFragments.add(mQuestionFragment);
        }
        return mFragments;
    }

    @Override
    public int gotoLastQuestion(Context mCtx) {
        return (int) SessionData.getObject(mCtx, Constant.SP_STUDY_LAST_CURRENT,0);
    }

    @Override
    public boolean showAdvert() {
        int persent= (int) (Math.random()*1000);
        return persent<=10;
    }

    @Override
    public void hideAdvert() {

    }
}

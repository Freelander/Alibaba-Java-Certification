package com.gojun.certification.view;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.gojun.certification.R;
import com.gojun.certification.core.DataManager;
import com.gojun.certification.core.SessionData;
import com.gojun.certification.frame.SingleLiveEvent;
import com.gojun.certification.global.Constant;
import com.gojun.certification.model.QuestionModel;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author Jun 12/5/20
 */
public class StudyViewModel extends MainViewModel {

    private final MutableLiveData<ArrayList<QuestionModel>> mListResource = new MutableLiveData<>();
    private final ObservableBoolean mLoadVisible = new ObservableBoolean(false);
    private final ObservableBoolean mDeleteVisible = new ObservableBoolean(false);
    private final ObservableArrayList<String> mStartTipText = new ObservableArrayList<>();
    private final SingleLiveEvent<Boolean> mClickStartEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> mClickDeleteEvent = new SingleLiveEvent<>();

    public StudyViewModel(@NonNull Application application) {
        super(application);
        mStartTipText.add(0, "");
        setCurrentItemId(R.id.tab_study);
    }

    public ObservableBoolean getLoadVisible() {
        return mLoadVisible;
    }

    public ObservableBoolean getDeleteVisible() {
        return mDeleteVisible;
    }

    public MutableLiveData<ArrayList<QuestionModel>> getListResource() {
        return mListResource;
    }

    public ObservableArrayList<String> getStartTipText() {
        return mStartTipText;
    }

    public SingleLiveEvent<Boolean> getClickStartEvent() {
        return mClickStartEvent;
    }

    public SingleLiveEvent<Boolean> getClickDeleteEvent() {
        return mClickDeleteEvent;
    }

    public void sendClickStartStudyEvent() {
        mClickStartEvent.setValue(true);
    }

    public void sendClickDeleteEvent() {
        mClickDeleteEvent.setValue(true);
    }

    public void loadData(Context context) {
        mLoadVisible.set(true);
        DataManager.getInstance().readListAsync(context, Constant.CACHE_HISTORY_STUDY, new DataManager.OnReadListener() {
            @Override
            public void onSuccess(Object mObj) {

                ArrayList<QuestionModel> list = (ArrayList<QuestionModel>) mObj;
                mLoadVisible.set(false);

                if (list != null && list.size() > 0) {

                    int current = (int) SessionData.getObject(context, Constant.SP_STUDY_LAST_CURRENT, 0);

                    mStartTipText.add(0, current == 0 ? "开始练习" : "上次做到第" + current + "题");

                    mDeleteVisible.set(true);
                } else {
                    mDeleteVisible.set(false);

                    mStartTipText.add(0, "");
                }
            }
        });
    }
}

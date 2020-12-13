package com.gojun.certification.view;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.gojun.certification.R;
import com.gojun.certification.core.DataManager;
import com.gojun.certification.core.async.XAsyncTask;
import com.gojun.certification.core.async.XAsyncTaskListenerCompat;
import com.gojun.certification.global.Constant;
import com.gojun.certification.model.QuestionModel;
import com.gojun.certification.utils.AppConstants;
import com.gojun.certification.utils.IntentHelper;
import com.gojun.certification.view.fial.FailAct;
import com.gojun.certification.widget.XDialog;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author Jun 12/2/20
 */
public class FailTopicViewModel extends MainViewModel {

    private final MutableLiveData<ArrayList<QuestionModel>> mListResource = new MutableLiveData<>();

    private final ObservableBoolean mEmptyLayoutVisibility = new ObservableBoolean(true);
    private final ObservableArrayList<String> mFailTopicText = new ObservableArrayList<>();
    private final ObservableArrayList<String> mEmptyText = new ObservableArrayList<>();

    private ArrayList<QuestionModel> mList;

    public FailTopicViewModel(@NonNull Application application) {
        super(application);
        mEmptyText.add(0, "");
        mFailTopicText.add(0, "");
        setCurrentItemId(R.id.tab_failed);
    }

    public MutableLiveData<ArrayList<QuestionModel>> getListResource() {
        return mListResource;
    }

    public ObservableBoolean getEmptyLayoutVisibility() {
        return mEmptyLayoutVisibility;
    }

    public ObservableArrayList<String> getFailTopicText() {
        return mFailTopicText;
    }

    public ObservableArrayList<String> getEmptyText() {
        return mEmptyText;
    }

    public void loadData(Context context) {
        mEmptyText.add(0, "加载中...");
        DataManager.getInstance().readListAsync(context, Constant.CACHE_FAIL_DATA, new DataManager.OnReadListener() {
            @Override
            public void onSuccess(Object mObj) {
                mList = (ArrayList<QuestionModel>) mObj;
                mListResource.setValue(mList);
            }
        });
    }

    public void clickDoFailTopic(Fragment fragment) {
        Bundle args = new Bundle();
        args.putSerializable(IntentHelper.KEY1, mList);
        IntentHelper.openClassResult(fragment.requireContext(), FailAct.class, AppConstants.REQUEST_CODE, args);
    }

    public void clickClearFailTopic(Fragment fragment) {
        final Context context = fragment.requireContext();
        XDialog.showSelectDialog(context, "是否清空错题记录", new XDialog.DialogClickListener() {
            @Override
            public void confirm() {
                clearFailTopic(context);
            }

            @Override
            public void cancel() {

            }
        });
    }

    private void clearFailTopic(Context context) {
        XAsyncTask.execute(context, new XAsyncTaskListenerCompat<Object, Object>() {
            @Override
            public Object doInBackground(Context context, Object... objects) {
                DataManager.getInstance().saveList(context, Constant.CACHE_FAIL_DATA, new ArrayList<>());
                return null;
            }

            @Override
            public void onPostExecute(Context context, Object o) {
                loadData(context);
            }
        });
    }
}

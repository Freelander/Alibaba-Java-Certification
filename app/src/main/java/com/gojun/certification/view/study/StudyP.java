package com.gojun.certification.view.study;

import com.gojun.certification.model.QuestionModel;

import java.util.ArrayList;

/**
 * Created by Porster on 17/3/31.
 */

public interface StudyP {

    void handleQuestion(ArrayList<QuestionModel> models);

    void gotoLastQuestion();

    void cehckNeedShowAdvert();
}

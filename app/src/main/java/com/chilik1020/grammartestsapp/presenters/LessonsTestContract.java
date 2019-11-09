package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.abstracts.MvpPresenter;
import com.chilik1020.grammartestsapp.abstracts.MvpView;
import com.chilik1020.grammartestsapp.model.entities.LessonTest;
import com.chilik1020.grammartestsapp.model.entities.Score;

import java.util.List;

public interface LessonsTestContract {
    interface View extends MvpView {
        void setData(List<LessonTest> data);
        void setRates(List<Score> rates);
    }

    interface Presenter extends MvpPresenter<View> {
        void loadData();
    }
}

package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.abstracts.MvpPresenter;
import com.chilik1020.grammartestsapp.abstracts.MvpView;
import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.model.entities.Test;

import java.util.List;

public interface LessonTestsContract {
    interface View extends MvpView {
        void setData(List<Test> data);
        void setRates(List<Score> rates);
        void setAppStatusPurchased(boolean isPurchased);
    }

    interface Presenter extends MvpPresenter<View> {
        void loadData(long id);
    }
}

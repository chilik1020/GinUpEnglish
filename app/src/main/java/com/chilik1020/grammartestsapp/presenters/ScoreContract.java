package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.abstracts.MvpPresenter;
import com.chilik1020.grammartestsapp.abstracts.MvpView;
import com.chilik1020.grammartestsapp.model.entities.Score;

import java.util.List;
import java.util.Map;

public interface ScoreContract {
    interface View extends MvpView {
        void setParentsData(List<List<Score>> data);
        void setChildsData(List<List<Score>> data);
        void setChapters(Map<Integer, String> data);
        void setLessons(Map<Integer, String> data);
    }

    interface Presenter extends MvpPresenter<View> {
        void loadData();
        void resetScore();
    }
}

package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.abstracts.MvpPresenter;
import com.chilik1020.grammartestsapp.abstracts.MvpView;
import com.chilik1020.grammartestsapp.model.entities.Chapter;

import java.util.List;

public interface ChapterContract {

    interface View extends MvpView {
        void setData(List<Chapter> data);
    }

    interface Presenter extends MvpPresenter<View>{
        void loadData();
    }
}

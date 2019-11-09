package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.abstracts.MvpPresenter;
import com.chilik1020.grammartestsapp.abstracts.MvpView;
import com.chilik1020.grammartestsapp.model.entities.LessonGrammar;

import java.util.List;

public interface LessonGrammarContract {
    interface View extends MvpView {
        void setData(List<LessonGrammar> data);
    }

    interface Presenter extends MvpPresenter<View> {
        void loadData(long id);
    }
}

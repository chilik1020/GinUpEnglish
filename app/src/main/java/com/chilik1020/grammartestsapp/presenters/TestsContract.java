package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.abstracts.MvpPresenter;
import com.chilik1020.grammartestsapp.abstracts.MvpView;
import com.chilik1020.grammartestsapp.model.entities.Question;
import com.chilik1020.grammartestsapp.model.entities.Score;

import java.util.List;

public interface TestsContract {
    interface View extends MvpView {
        void setData(List<Question> data);
        void setTopic(String topic);
    }
    interface Presenter extends MvpPresenter<View> {
        void loadData(int typeTest, int number, int chapterId, int lessonId, int testId);
        void saveResult(Score score);
    }
}

package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.model.repository.LessonGrammarRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LessonsGrammarPresenter implements LessonGrammarContract.Presenter {

    @Inject
    public LessonGrammarRepository lessonGrammarRepository;

    private LessonGrammarContract.View view;

    public LessonsGrammarPresenter(LessonGrammarRepository lessonGrammarRepository) {
        this.lessonGrammarRepository = lessonGrammarRepository;
    }

    @Override
    public void attachView(LessonGrammarContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadData(long id) {
        lessonGrammarRepository.getLessonsByChapterId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lessonGrammars -> view.setData(lessonGrammars));
    }
}

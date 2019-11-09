package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.model.repository.LessonTestRepository;
import com.chilik1020.grammartestsapp.model.repository.ScoreRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LessonsTestPresenter implements LessonsTestContract.Presenter {

    @Inject
    public LessonTestRepository lessonTestRepository;

    @Inject
    public ScoreRepository scoreRepository;

    private LessonsTestContract.View view;

    public LessonsTestPresenter(LessonTestRepository lessonTestRepository, ScoreRepository scoreRepository) {
        this.lessonTestRepository = lessonTestRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void attachView(LessonsTestContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadData() {
        Disposable subscribe = lessonTestRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lessonTests -> view.setData(lessonTests));

        Disposable subscribe1 = scoreRepository.getSingleScoresByTypeResultAndTestId(2, -1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rates -> view.setRates(rates));

    }
}

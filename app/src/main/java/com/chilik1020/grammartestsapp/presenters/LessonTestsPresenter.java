package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.model.repository.ScoreRepository;
import com.chilik1020.grammartestsapp.model.repository.TestRepository;
import com.chilik1020.grammartestsapp.utils.AppStatusUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LessonTestsPresenter implements LessonTestsContract.Presenter {

    @Inject
    public AppStatusUtil appStatusUtil;

    @Inject
    public TestRepository testRepository;

    @Inject
    public ScoreRepository scoreRepository;

    private LessonTestsContract.View view;

    public LessonTestsPresenter(AppStatusUtil appStatusUtil, TestRepository testRepository, ScoreRepository scoreRepository) {
        this.appStatusUtil = appStatusUtil;
        this.testRepository = testRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void attachView(LessonTestsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadData(long id) {
        Disposable subscribe = testRepository.getSingleTestsByLessonId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tests -> view.setData(tests));

        Disposable subscribe1 = scoreRepository.getSingleScoresByTypeResultAndLessonIdAndPositiveTestId(2, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rates -> view.setRates(rates));

        view.setAppStatusPurchased(appStatusUtil.isAllTestPurchased());
    }
}

package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.model.repository.ChapterRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChaptersPresenter implements ChapterContract.Presenter {

    @Inject
    public ChapterRepository chapterRepository;

    private ChapterContract.View view;

    public ChaptersPresenter(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @Override
    public void attachView(ChapterContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadData() {
        Disposable disposable = chapterRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chapters -> view.setData(chapters));
    }
}

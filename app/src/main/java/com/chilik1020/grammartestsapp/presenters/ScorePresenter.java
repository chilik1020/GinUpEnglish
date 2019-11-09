package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.model.entities.Chapter;
import com.chilik1020.grammartestsapp.model.entities.LessonTest;
import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.model.repository.ChapterRepository;
import com.chilik1020.grammartestsapp.model.repository.LessonTestRepository;
import com.chilik1020.grammartestsapp.model.repository.ScoreRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ScorePresenter implements ScoreContract.Presenter {

    @Inject
    public ScoreRepository scoreRepository;

    @Inject
    public ChapterRepository chapterRepository;

    @Inject
    public LessonTestRepository lessonTestRepository;

    private ScoreContract.View view;

    public ScorePresenter(ScoreRepository scoreRepository, ChapterRepository chapterRepository, LessonTestRepository lessonTestRepository) {
        this.scoreRepository = scoreRepository;
        this.chapterRepository = chapterRepository;
        this.lessonTestRepository = lessonTestRepository;
    }

    @Override
    public void attachView(ScoreContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadData() {
        List<List<Score>> groupData = new ArrayList<>();
        List<List<Score>> childData = new ArrayList<>();
        Disposable subscribe = scoreRepository.getSingleScoresByTestTypeAndTypeResult(0, 1)
                .mergeWith(scoreRepository.getSingleScoresByTestTypeAndTypeResultAndChapterId(1, 1, -1))
                .mergeWith(scoreRepository.getSingleScoresByTestTypeAndTypeResultAndLessonId(2, 1, -1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    groupData.add(item);
                    view.setParentsData(groupData);
                });

        Disposable subscribe1 = scoreRepository.getSingleScoresByTestTypeAndTypeResult(0, 0)
                .mergeWith(scoreRepository.getSingleScoresByTestTypeAndTypeResultWhereChapterIdPositive(1, 1))
                .mergeWith(scoreRepository.getSingleScoresByTestTypeAndTypeResultWhereLessonIdPositive(2, 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    childData.add(item);
                    view.setChildsData(childData);
                });

        Disposable subscribe2 = chapterRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chapters -> {
                    Map<Integer, String> chaptersTemp = new HashMap<>();
                    for (Chapter ch : chapters) {
                        chaptersTemp.put(ch.get_id(), ch.getChapter());
                    }
                    view.setChapters(chaptersTemp);
                });

        Disposable subscribe3 = lessonTestRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lessonTests -> {
                    Map<Integer, String> lessonsTestsTemp = new HashMap<>();
                    for (LessonTest lt : lessonTests) {
                        lessonsTestsTemp.put(lt.getId(), lt.getTopic());
                    }
                    view.setLessons(lessonsTestsTemp);
                });
    }

    @Override
    public void resetScore() {
        Disposable subscribe = scoreRepository.deleteAllScores()
                .subscribeOn(Schedulers.io())
                .subscribe(this::loadData);
    }
}

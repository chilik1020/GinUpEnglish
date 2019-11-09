package com.chilik1020.grammartestsapp.presenters;

import com.chilik1020.grammartestsapp.App;
import com.chilik1020.grammartestsapp.model.entities.Question;
import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.model.repository.ChapterRepository;
import com.chilik1020.grammartestsapp.model.repository.LessonTestRepository;
import com.chilik1020.grammartestsapp.model.repository.QuestionReporitory;
import com.chilik1020.grammartestsapp.utils.AppStatusUtil;
import com.chilik1020.grammartestsapp.utils.ScoreSaver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TestsPresenter implements TestsContract.Presenter {

    @Inject
    public AppStatusUtil appStatusUtil;

    @Inject
    public QuestionReporitory questionReporitory;

    @Inject
    public ChapterRepository chapterRepository;

    @Inject
    public LessonTestRepository lessonTestRepository;

    @Inject
    public ScoreSaver scoreSaver;

    private TestsContract.View view;

    public TestsPresenter(AppStatusUtil appStatusUtil, QuestionReporitory questionReporitory, ChapterRepository chapterRepository, LessonTestRepository lessonTestRepository, ScoreSaver scoreSaver) {
        this.appStatusUtil = appStatusUtil;
        this.questionReporitory = questionReporitory;
        this.chapterRepository = chapterRepository;
        this.lessonTestRepository = lessonTestRepository;
        this.scoreSaver = scoreSaver;
    }

    @Override
    public void attachView(TestsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadData(int typeTest, int number, int chapterId, int lessonId, int testId) {
        switch (typeTest) {
            case 0 :
                if (appStatusUtil.isAllTestPurchased()) {
                    Disposable subscribe = questionReporitory.getAllQuestions()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(questions -> view.setData(getRandomQuestions(number, questions)));

                } else {
                    Disposable subscribe = questionReporitory.getFreeQuestions()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(questions -> view.setData(getRandomQuestions(number, questions)));
                }
                view.setTopic(number + " random questions");
                break;

            case 1 :
                if (appStatusUtil.isAllTestPurchased()) {
                    Disposable subscribe = questionReporitory.getAllQuestionsByChapterId(chapterId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(questions -> view.setData(getRandomQuestions(number, questions)));
                }
                else {
                    Disposable subscribe = questionReporitory.getFreeQuestionByChapterId(chapterId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(questions -> view.setData(getRandomQuestions(number, questions)));
                }

                chapterRepository.getChapterTopicById(chapterId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> view.setTopic(s + "\n"+ number + " random questions"));
                break;

            case 2 :
                questionReporitory.getQuestionByTestId(testId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(questions -> view.setData(getRandomQuestions(number,questions)));

                lessonTestRepository.getTopicById(lessonId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> view.setTopic(s));
                break;
        }
    }

    @Override
    public void saveResult(Score score) {
        Disposable subscribe = Observable.just(score)
                .subscribeOn(Schedulers.io())
                .subscribe(score1 -> scoreSaver.saveResultAndUpdateDBMeanResult(score1));
    }

    private List<Question> getRandomQuestions(int number, List<Question> data) {
        if (data.size() <= 10)
            return data;

        List<Question> randomData = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Question q = data.get((int) (Math.random()*data.size()));
            randomData.add(q);
            data.remove(q);
        }
        return randomData;
    }
}

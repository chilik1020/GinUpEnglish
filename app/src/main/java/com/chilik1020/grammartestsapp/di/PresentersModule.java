package com.chilik1020.grammartestsapp.di;

import com.chilik1020.grammartestsapp.model.repository.ChapterRepository;
import com.chilik1020.grammartestsapp.model.repository.LessonGrammarRepository;
import com.chilik1020.grammartestsapp.model.repository.LessonTestRepository;
import com.chilik1020.grammartestsapp.model.repository.QuestionReporitory;
import com.chilik1020.grammartestsapp.model.repository.ScoreRepository;
import com.chilik1020.grammartestsapp.model.repository.TestRepository;
import com.chilik1020.grammartestsapp.presenters.ChaptersPresenter;
import com.chilik1020.grammartestsapp.presenters.LessonTestsPresenter;
import com.chilik1020.grammartestsapp.presenters.LessonsGrammarPresenter;
import com.chilik1020.grammartestsapp.presenters.LessonsTestPresenter;
import com.chilik1020.grammartestsapp.presenters.ScorePresenter;
import com.chilik1020.grammartestsapp.presenters.TestsPresenter;
import com.chilik1020.grammartestsapp.utils.AppStatusUtil;
import com.chilik1020.grammartestsapp.utils.ScoreSaver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentersModule {

    @Singleton
    @Provides
    ChaptersPresenter provideChaptersPresenter(ChapterRepository chapterRepository) {
        return new ChaptersPresenter(chapterRepository);
    }

    @Singleton
    @Provides
    LessonsGrammarPresenter provideLessonGrammarPresenter(LessonGrammarRepository lessonGrammarRepository){
        return new LessonsGrammarPresenter(lessonGrammarRepository);
    }

    @Singleton
    @Provides
    LessonsTestPresenter provideLessonTestPresenter(LessonTestRepository lessonTestRepository, ScoreRepository scoreRepository){
        return new LessonsTestPresenter(lessonTestRepository, scoreRepository);
    }

    @Singleton
    @Provides
    LessonTestsPresenter provideLessonTestsPresenter(AppStatusUtil appStatusUtil, TestRepository testRepository, ScoreRepository scoreRepository) {
        return new LessonTestsPresenter(appStatusUtil ,testRepository, scoreRepository);
    }

    @Provides
    @Singleton
    TestsPresenter provideTestsPresenter(AppStatusUtil appStatusUtil, QuestionReporitory questionReporitory, ChapterRepository chapterRepository, LessonTestRepository lessonTestRepository, ScoreSaver scoreSaver) {
        return new TestsPresenter(appStatusUtil, questionReporitory, chapterRepository, lessonTestRepository, scoreSaver);
    }

    @Singleton
    @Provides
    ScorePresenter provideScorePresenter(ScoreRepository scoreRepository, ChapterRepository chapterRepository, LessonTestRepository lessonTestRepository) {
        return new ScorePresenter(scoreRepository, chapterRepository, lessonTestRepository);
    }

}

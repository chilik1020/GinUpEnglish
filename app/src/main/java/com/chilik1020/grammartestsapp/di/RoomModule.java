package com.chilik1020.grammartestsapp.di;

import android.app.Application;

import androidx.room.Room;

import com.chilik1020.grammartestsapp.model.dao.ChapterDao;
import com.chilik1020.grammartestsapp.model.dao.LessonGrammarDao;
import com.chilik1020.grammartestsapp.model.dao.LessonTestDao;
import com.chilik1020.grammartestsapp.model.dao.PurchaseDao;
import com.chilik1020.grammartestsapp.model.dao.QuestionDao;
import com.chilik1020.grammartestsapp.model.dao.ScoreDao;
import com.chilik1020.grammartestsapp.model.dao.TestDao;
import com.chilik1020.grammartestsapp.model.dao.UserStatDao;
import com.chilik1020.grammartestsapp.model.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.model.db.AppPersonalDataDatabase;
import com.chilik1020.grammartestsapp.model.repository.ChapterRepository;
import com.chilik1020.grammartestsapp.model.repository.ChapterRepositoryImpl;
import com.chilik1020.grammartestsapp.model.repository.LessonGrammarRepository;
import com.chilik1020.grammartestsapp.model.repository.LessonGrammarRepositoryImpl;
import com.chilik1020.grammartestsapp.model.repository.LessonTestRepository;
import com.chilik1020.grammartestsapp.model.repository.LessonTestRepositoryImpl;
import com.chilik1020.grammartestsapp.model.repository.PurchaseRepository;
import com.chilik1020.grammartestsapp.model.repository.PurchaseRepositoryImpl;
import com.chilik1020.grammartestsapp.model.repository.QuestionReporitory;
import com.chilik1020.grammartestsapp.model.repository.QuestionRepositoryImpl;
import com.chilik1020.grammartestsapp.model.repository.ScoreRepository;
import com.chilik1020.grammartestsapp.model.repository.ScoreRepositoryImpl;
import com.chilik1020.grammartestsapp.model.repository.TestRepository;
import com.chilik1020.grammartestsapp.model.repository.TestRepositoryImpl;
import com.chilik1020.grammartestsapp.model.repository.UserStatRepository;
import com.chilik1020.grammartestsapp.model.repository.UserStatRepositoryImpl;
import com.chilik1020.grammartestsapp.utils.AppConstant;
import com.chilik1020.grammartestsapp.utils.AppStatusUtil;
import com.chilik1020.grammartestsapp.utils.ScoreSaver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private AppGeneralDataDatabase appGeneralDataDatabase;

    private AppPersonalDataDatabase appPersonalDataDatabase;

    public RoomModule(Application mApplication){
        appGeneralDataDatabase = Room
                .databaseBuilder(mApplication, AppGeneralDataDatabase.class, AppConstant.APP_GENERAL_DB_NAME)
                .build();

        appPersonalDataDatabase = Room
                .databaseBuilder(mApplication, AppPersonalDataDatabase.class, AppConstant.APP_PERSONAL_DB_NAME)
                .build();
    }

    /*
        DB
     */

    @Provides
    @Singleton
    AppGeneralDataDatabase providesAppGeneralDatabase(){
        return appGeneralDataDatabase;
    }

    @Provides
    @Singleton
    AppPersonalDataDatabase provideAppPersonalDatabase() {
        return appPersonalDataDatabase;
    }


    /*
        DAO
     */
    @Provides
    @Singleton
    ChapterDao providesChapterDao(AppGeneralDataDatabase appGeneralDataDatabase){
        return appGeneralDataDatabase.chapterDao();
    }

    @Provides
    @Singleton
    LessonGrammarDao providesLessonGrammarDao(AppGeneralDataDatabase appGeneralDataDatabase){
        return appGeneralDataDatabase.lessonGrammarDao();
    }

    @Provides
    @Singleton
    LessonTestDao providesLessonTestDao(AppGeneralDataDatabase appGeneralDataDatabase){
        return appGeneralDataDatabase.lessonTestsDao();
    }

    @Provides
    @Singleton
    ScoreDao providesScoreDao(AppPersonalDataDatabase appPersonalDataDatabase) {
        return appPersonalDataDatabase.scoreDao();
    }

    @Provides
    @Singleton
    TestDao providesTestDao(AppGeneralDataDatabase appGeneralDataDatabase) {
        return appGeneralDataDatabase.testDao();
    }

    @Provides
    @Singleton
    QuestionDao providesQuestionDao(AppGeneralDataDatabase appGeneralDataDatabase){
        return appGeneralDataDatabase.questionDao();
    }

    @Provides
    @Singleton
    UserStatDao providesUserStatDao(AppPersonalDataDatabase appPersonalDataDatabase) {
        return appPersonalDataDatabase.userStatDao();
    }

    @Provides
    @Singleton
    PurchaseDao providesPurchaseDao(AppPersonalDataDatabase appPersonalDataDatabase) {
        return appPersonalDataDatabase.purchaseDao();
    }


    /*
        REPOSITORIES
     */
    @Provides
    @Singleton
    ChapterRepository providesChapterRepository(ChapterDao chapterDao){
        return new ChapterRepositoryImpl(chapterDao);
    }

    @Provides
    @Singleton
    LessonGrammarRepository providesLessonGrammarRepository(LessonGrammarDao lessonGrammarDao) {
        return new LessonGrammarRepositoryImpl(lessonGrammarDao);
    }

    @Provides
    @Singleton
    LessonTestRepository providesLessonTestRepository(LessonTestDao lessonTestDao) {
        return new LessonTestRepositoryImpl(lessonTestDao);
    }

    @Provides
    @Singleton
    ScoreRepository providesScoreRepository(ScoreDao scoreDao){
        return new ScoreRepositoryImpl(scoreDao);
    }

    @Provides
    @Singleton
    TestRepository providesTestRepository(TestDao testDao) {
        return new TestRepositoryImpl(testDao);
    }

    @Provides
    @Singleton
    QuestionReporitory providesQuestionRepoitory(QuestionDao questionDao) {
        return new QuestionRepositoryImpl(questionDao);
    }

    @Provides
    @Singleton
    UserStatRepository providesUserStatRepository(UserStatDao userStatDao) {
        return new UserStatRepositoryImpl(userStatDao);
    }

    @Provides
    @Singleton
    PurchaseRepository providesPurchaseRepository(PurchaseDao purchaseDao) {
        return new PurchaseRepositoryImpl(purchaseDao);
    }

    /*
        ScoreSaver
     */
    @Provides
    @Singleton
    ScoreSaver providesScoreSaver(ScoreRepository scoreRepository, TestRepository testRepository) {
        return new ScoreSaver(scoreRepository, testRepository);
    }

    /*
        App Status Util
     */
    @Provides
    @Singleton
    AppStatusUtil providesAppStatusUtil(UserStatRepository userStatRepository, PurchaseRepository purchaseRepository) {
        return new AppStatusUtil(userStatRepository, purchaseRepository);
    }
}

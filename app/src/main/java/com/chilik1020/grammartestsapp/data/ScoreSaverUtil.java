package com.chilik1020.grammartestsapp.data;

import com.chilik1020.grammartestsapp.data.dao.ScoreDao;
import com.chilik1020.grammartestsapp.data.dao.TestDao;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.data.db.AppPersonalDataDatabase;
import com.chilik1020.grammartestsapp.data.model.Score;
import com.chilik1020.grammartestsapp.data.model.Test;

import java.util.ArrayList;
import java.util.List;

public class ScoreSaverUtil {

    private static AppPersonalDataDatabase db;
    private static ScoreDao scoreDao;

    public static void saveResultAndUpdateDBMeanResult(Score st) {
        /*
          1. add current result of test into DB
          2. get all single results with same type from DB
          3. calculate mean result
          4. update or insert mean result into DB
          5. if "st" is result of chapters(lessons) test:
               call calculateAndStoreAllChaptersMeanResult(calculateAndStoreAllLessonsMeanResult)
          6. if st.testId >= 0 and result > "maximum result" then (update maximum result for testId)
         */

        /*
          1
         */

        db = App.getInstance().getAppPersonalDataDatabase();
        scoreDao = db.scoreDao();
        scoreDao.insert(st);
        /*
          2
         */
        List<Score> allScoreSameType = new ArrayList<>();

        switch (st.getTypeTest()) {
            case 0 :
                allScoreSameType = scoreDao.getScoresByTestTypeAndTypeResult(st.getTypeTest(),st.getTypeResult());
                break;
            case 1 :
                allScoreSameType = scoreDao.getScoresByTestTypeAndTypeResultAndChapterId(st.getTypeTest(), st.getTypeResult(), st.getChapterId());
                break;
            case 2 :
                allScoreSameType = scoreDao.getScoresByTestTypeAndTypeResultAndLessonId(st.getTypeTest(), st.getTypeResult(), st.getLessonId());
                break;
        }

        /*
          3 and 4
         */

        Score stMean = scoreDao.getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(st.getTypeTest(), 1, st.getChapterId(), st.getLessonId());
        if (stMean == null) {
            stMean = new Score(st.getTypeTest(), 1, st.getChapterId(), st.getLessonId(), st.getTestId(), st.getResult());
            scoreDao.insert(stMean);
        } else {
            int meanResult = 0;
            for (Score s: allScoreSameType) {
            meanResult = meanResult + s.getResult();
            }
            meanResult = meanResult/allScoreSameType.size();
            stMean.setResult(meanResult);
            scoreDao.update(stMean);
        }

        /*
          5
         */
        switch(st.getTypeTest()) {
            case 1:
                calculateAndStoreAllChaptersMeanResult();
                break;
            case 2:
                calculateAndStoreAllLessonsMeanResult();
                break;
        }

        /*
          6
         */
        if (st.getTestId() >= 0) {
            checkAndUpdateMaximumResultForTest(st);
            updateRatesForLesson(st);
        }
    }

    private static void calculateAndStoreAllLessonsMeanResult() {
        /*
          1. get all lessons result (typeTest = 2, typeResult = 0, lessons_id > 0)
          2. calculate mean result
          3. update or insert mean result
         */
        List<Score> allLessonsScores = scoreDao.getScoresByTestTypeAndTypeResultWhereLessonIdPositive(2, 0);
        int meanResult = 0;

        if (allLessonsScores == null)
            return;


        if (allLessonsScores.size() > 0) {
            for (Score s: allLessonsScores) {
                meanResult = meanResult + s.getResult();
            }
            meanResult = meanResult/allLessonsScores.size();
        }

        Score stMean = scoreDao.getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(2,1,-1,-1);
        if (stMean == null) {
            stMean = new Score(2, 1, -1, -1, -1, meanResult);
            scoreDao.insert(stMean);
        } else {
            stMean.setResult(meanResult);
            scoreDao.update(stMean);
        }
    }

    private static void calculateAndStoreAllChaptersMeanResult() {
        /*
          1. get all chapters result (typeTest = 1, typeResult = 0, chapter_id > 0)
          2. calculate mean result
          3. update or insert mean result
         */
        List<Score> allChaptersScores = scoreDao.getScoresByTestTypeAndTypeResultWhereChapterIdPositive(1, 0);
        int meanResult = 0;

        if (allChaptersScores == null)
            return;

        if (allChaptersScores.size() > 0) {
            for (Score s: allChaptersScores) {
                meanResult = meanResult + s.getResult();
            }
            meanResult = meanResult/allChaptersScores.size();
        }
        Score stMean = scoreDao.getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(1,1,-1,-1);

        if (stMean == null) {
            stMean = new Score(1, 1, -1, -1, -1, meanResult);
            scoreDao.insert(stMean);
        } else {
            stMean.setResult(meanResult);
            scoreDao.update(stMean);
        }
    }

    private static void checkAndUpdateMaximumResultForTest(Score st) {
        Score stMax = scoreDao.getScoreByTypeResultAndTestId(2, st.getTestId());
        if (stMax == null) {
            stMax = new Score(st.getTypeTest(), 2, st.getChapterId(), st.getLessonId(), st.getTestId(), st.getResult());
            scoreDao.insert(stMax);
        } else {
            int currentMax = stMax.getResult();
            int currentResult = st.getResult();
            if (currentResult > currentMax) {
                stMax.setResult(currentResult);
                scoreDao.update(stMax);
            }
        }
    }

    private static void updateRatesForLesson(Score st) {
        /*
        1. Get tests with lessonId(number of tests)
        2. Get all maximum results with lessonId
        3. (Sum of maximum results / number of tests ) = rate for lesson
        4. Insert or update rate for lesson
         */
        AppGeneralDataDatabase generalDataDatabase = App.getInstance().getAppGeneralDataDatabase();
        TestDao testDao = generalDataDatabase.testDao();
        List<Test> tests = testDao.getTestsByLessonId(st.getLessonId());
        int numberOfTests = tests.size();

        List<Score> maxScores = scoreDao.getScoresByTypeResultAndLessonIdAndPositiveTestId(2,st.getLessonId());
        int sumOfMaxScores = 0;
        for (Score s: maxScores) {
            sumOfMaxScores += s.getResult();
        }

        int rateForLesson = sumOfMaxScores / numberOfTests;

        Score stMax = scoreDao.getScoresByTypeResultAndLessonIdAndTestId(2, st.getLessonId(),-1);
        if (stMax == null) {
            stMax = new Score(st.getTypeTest(), 2, st.getChapterId(), st.getLessonId(), -1, rateForLesson);
            scoreDao.insert(stMax);
        } else {
            int currentMax = stMax.getResult();

            if (rateForLesson > currentMax) {
                stMax.setResult(rateForLesson);
                scoreDao.update(stMax);
            }
        }
    }
}

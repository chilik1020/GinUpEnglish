package com.chilik1020.grammartestsapp.utils;

import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.model.entities.Test;
import com.chilik1020.grammartestsapp.model.repository.ScoreRepository;
import com.chilik1020.grammartestsapp.model.repository.TestRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ScoreSaver {

    @Inject
    public ScoreRepository scoreRepository;

    @Inject
    public TestRepository testRepository;

    public ScoreSaver(ScoreRepository scoreRepository, TestRepository testRepository) {
        this.scoreRepository = scoreRepository;
        this.testRepository = testRepository;
    }

    public void saveResultAndUpdateDBMeanResult(Score st) {
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

        scoreRepository.insert(st);
        /*
          2
         */
        List<Score> allScoreSameType = new ArrayList<>();

        switch (st.getTypeTest()) {
            case 0 :
                allScoreSameType = scoreRepository.getScoresByTestTypeAndTypeResult(st.getTypeTest(),st.getTypeResult());
                break;
            case 1 :
                allScoreSameType = scoreRepository.getScoresByTestTypeAndTypeResultAndChapterId(st.getTypeTest(), st.getTypeResult(), st.getChapterId());
                break;
            case 2 :
                allScoreSameType = scoreRepository.getScoresByTestTypeAndTypeResultAndLessonId(st.getTypeTest(), st.getTypeResult(), st.getLessonId());
                break;
        }

        /*
          3 and 4
         */

        Score stMean = scoreRepository.getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(st.getTypeTest(), 1, st.getChapterId(), st.getLessonId());
        if (stMean == null) {
            stMean = new Score(st.getTypeTest(), 1, st.getChapterId(), st.getLessonId(), st.getTestId(), st.getResult());
            scoreRepository.insert(stMean);
        } else {
            int meanResult = 0;
            for (Score s: allScoreSameType) {
            meanResult = meanResult + s.getResult();
            }
            meanResult = meanResult/allScoreSameType.size();
            stMean.setResult(meanResult);
            scoreRepository.update(stMean);
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

    private void calculateAndStoreAllLessonsMeanResult() {
        /*
          1. get all lessons result (typeTest = 2, typeResult = 0, lessons_id > 0)
          2. calculate mean result
          3. update or insert mean result
         */
        List<Score> allLessonsScores = scoreRepository.getScoresByTestTypeAndTypeResultWhereLessonIdPositive(2, 0);
        int meanResult = 0;

        if (allLessonsScores == null)
            return;


        if (allLessonsScores.size() > 0) {
            for (Score s: allLessonsScores) {
                meanResult = meanResult + s.getResult();
            }
            meanResult = meanResult/allLessonsScores.size();
        }

        Score stMean = scoreRepository.getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(2,1,-1,-1);
        if (stMean == null) {
            stMean = new Score(2, 1, -1, -1, -1, meanResult);
            scoreRepository.insert(stMean);
        } else {
            stMean.setResult(meanResult);
            scoreRepository.update(stMean);
        }
    }

    private void calculateAndStoreAllChaptersMeanResult() {
        /*
          1. get all chapters result (typeTest = 1, typeResult = 0, chapter_id > 0)
          2. calculate mean result
          3. update or insert mean result
         */
        List<Score> allChaptersScores = scoreRepository.getScoresByTestTypeAndTypeResultWhereChapterIdPositive(1, 0);
        int meanResult = 0;

        if (allChaptersScores == null)
            return;

        if (allChaptersScores.size() > 0) {
            for (Score s: allChaptersScores) {
                meanResult = meanResult + s.getResult();
            }
            meanResult = meanResult/allChaptersScores.size();
        }
        Score stMean = scoreRepository.getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(1,1,-1,-1);

        if (stMean == null) {
            stMean = new Score(1, 1, -1, -1, -1, meanResult);
            scoreRepository.insert(stMean);
        } else {
            stMean.setResult(meanResult);
            scoreRepository.update(stMean);
        }
    }

    private void checkAndUpdateMaximumResultForTest(Score st) {
        Score stMax = scoreRepository.getScoreByTypeResultAndTestId(2, st.getTestId());
        if (stMax == null) {
            stMax = new Score(st.getTypeTest(), 2, st.getChapterId(), st.getLessonId(), st.getTestId(), st.getResult());
            scoreRepository.insert(stMax);
        } else {
            int currentMax = stMax.getResult();
            int currentResult = st.getResult();
            if (currentResult > currentMax) {
                stMax.setResult(currentResult);
                scoreRepository.update(stMax);
            }
        }
    }

    private void updateRatesForLesson(Score st) {
        /*
        1. Get tests with lessonId(number of tests)
        2. Get all maximum results with lessonId
        3. (Sum of maximum results / number of tests ) = rate for lesson
        4. Insert or update rate for lesson
         */
        List<Test> tests = testRepository.getTestsByLessonId(st.getLessonId());
        int numberOfTests = tests.size();

        List<Score> maxScores = scoreRepository.getScoresByTypeResultAndLessonIdAndPositiveTestId(2,st.getLessonId());
        int sumOfMaxScores = 0;
        for (Score s: maxScores) {
            sumOfMaxScores += s.getResult();
        }

        int rateForLesson = sumOfMaxScores / numberOfTests;

        Score stMax = scoreRepository.getScoresByTypeResultAndLessonIdAndTestId(2, st.getLessonId(),-1);
        if (stMax == null) {
            stMax = new Score(st.getTypeTest(), 2, st.getChapterId(), st.getLessonId(), -1, rateForLesson);
            scoreRepository.insert(stMax);
        } else {
            int currentMax = stMax.getResult();

            if (rateForLesson > currentMax) {
                stMax.setResult(rateForLesson);
                scoreRepository.update(stMax);
            }
        }
    }
}

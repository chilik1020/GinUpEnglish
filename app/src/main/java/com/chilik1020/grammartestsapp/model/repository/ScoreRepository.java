package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.entities.Score;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ScoreRepository {

    /*
        Queries for ScoreSaver
     */
    void insert(Score score);
    void update(Score score);
    Score getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(int tt, int tr, int chapterId, int lessonId);
    List<Score> getScoresByTestTypeAndTypeResult(int tt, int tr);
    List<Score> getScoresByTestTypeAndTypeResultAndChapterId(int tt, int tr, int chapterId);
    List<Score> getScoresByTestTypeAndTypeResultAndLessonId(int tt, int tr, int lessonId);
    List<Score> getScoresByTestTypeAndTypeResultWhereLessonIdPositive(int tt, int tr);
    List<Score> getScoresByTestTypeAndTypeResultWhereChapterIdPositive(int tt, int tr);
    Score getScoreByTypeResultAndTestId(int tr, int testId);
    List<Score> getScoresByTypeResultAndLessonIdAndPositiveTestId(int tr, int lessonId);
    Score getScoresByTypeResultAndLessonIdAndTestId(int tr, int lessonId, int testId);

    /*
        Queries for ScoreFragment
     */
    Completable deleteAllScores();
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResult(int tt, int tr);
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResultAndChapterId(int tt, int tr, int chapterId);
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResultAndLessonId(int tt, int tr, int lessonId);
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResultWhereLessonIdPositive(int tt, int tr);
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResultWhereChapterIdPositive(int tt, int tr);

    /*
    Query for stars on LessonTestsFragment
    */
    Single<List<Score>> getSingleScoresByTypeResultAndTestId(int tr, int testId);
    /*
        Query for stars on LessonsTestsFragment
    */
    Single<List<Score>> getSingleScoresByTypeResultAndLessonIdAndPositiveTestId(int tr, long lessonId);
}

package com.chilik1020.grammartestsapp.data.dao;

import com.chilik1020.grammartestsapp.data.model.Score;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ScoreDao {

    /*
        Queries for ScoreSaverUtil
     */
    @Insert
    void insert(Score score);

    @Update
    void update(Score score);

    @Query("SELECT * FROM scores WHERE typeTest = :tt AND typeResult = :tr AND chapterId = :chapterId AND lessonId = :lessonId")
    Score getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(int tt, int tr, int chapterId, int lessonId);

    @Query("SELECT * FROM scores WHERE typeTest = :tt and typeResult = :tr")
    List<Score> getScoresByTestTypeAndTypeResult(int tt, int tr);

    @Query("SELECT * FROM scores WHERE typeTest = :tt and typeResult = :tr and chapterId = :chapterId")
    List<Score> getScoresByTestTypeAndTypeResultAndChapterId(int tt, int tr, int chapterId);

    @Query("SELECT * FROM scores WHERE typeTest = :tt and typeResult = :tr and lessonId = :lessonId")
    List<Score> getScoresByTestTypeAndTypeResultAndLessonId(int tt, int tr, int lessonId);

    @Query("SELECT * FROM scores WHERE typeTest = :tt AND typeResult = :tr AND lessonId >= 0")
    List<Score> getScoresByTestTypeAndTypeResultWhereLessonIdPositive(int tt, int tr);

    @Query("SELECT * FROM scores WHERE typeTest = :tt AND typeResult = :tr AND chapterId >= 0")
    List<Score> getScoresByTestTypeAndTypeResultWhereChapterIdPositive(int tt, int tr);

    @Query("SELECT * FROM scores WHERE typeResult = :tr AND testId = :testId")
    Score getScoreByTypeResultAndTestId(int tr, int testId);

    @Query("SELECT * FROM scores WHERE typeResult = :tr AND lessonId = :lessonId AND testId >= 0")
    List<Score> getScoresByTypeResultAndLessonIdAndPositiveTestId(int tr, int lessonId);

    @Query("SELECT * FROM scores WHERE typeResult = :tr AND lessonId = :lessonId AND testId = :testId")
    Score getScoresByTypeResultAndLessonIdAndTestId(int tr, int lessonId, int testId);

    /*
        Queries for ScoreFragment
     */
    @Query("DELETE FROM scores")
    Completable deleteAllScores();

    @Query("SELECT * FROM scores WHERE typeTest = :tt and typeResult = :tr")
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResult(int tt, int tr);

    @Query("SELECT * FROM scores WHERE typeTest = :tt and typeResult = :tr and chapterId = :chapterId")
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResultAndChapterId(int tt, int tr, int chapterId);

    @Query("SELECT * FROM scores WHERE typeTest = :tt and typeResult = :tr and lessonId = :lessonId")
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResultAndLessonId(int tt, int tr, int lessonId);

    @Query("SELECT * FROM scores WHERE typeTest = :tt AND typeResult = :tr AND lessonId >= 0")
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResultWhereLessonIdPositive(int tt, int tr);

    @Query("SELECT * FROM scores WHERE typeTest = :tt AND typeResult = :tr AND chapterId >= 0")
    Single<List<Score>> getSingleScoresByTestTypeAndTypeResultWhereChapterIdPositive(int tt, int tr);



    /*
        Query for stars on LessonTestsFragment
     */
    @Query("SELECT * FROM scores WHERE typeResult = :tr AND lessonId = :lessonId AND testId >= 0")
    Single<List<Score>> getSingleScoresByTypeResultAndLessonIdAndPositiveTestId(int tr, int lessonId);

    /*
        Query for stars on LessonsTestsFragment
    */
    @Query("SELECT * FROM scores WHERE typeResult = :tr AND testId = :testId")
    Single<List<Score>> getSingleScoresByTypeResultAndTestId(int tr, int testId);
}

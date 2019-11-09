package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.dao.ScoreDao;
import com.chilik1020.grammartestsapp.model.entities.Score;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ScoreRepositoryImpl implements ScoreRepository {

    private ScoreDao scoreDao;

    public ScoreRepositoryImpl(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    @Override
    public void insert(Score score) {
        scoreDao.insert(score);
    }

    @Override
    public void update(Score score) {
        scoreDao.update(score);
    }

    @Override
    public Score getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(int tt, int tr, int chapterId, int lessonId) {
        return scoreDao.getScoreByTestTypeAndTypeResultAndChapterIdAndLessonId(tt, tr, chapterId, lessonId);
    }

    @Override
    public List<Score> getScoresByTestTypeAndTypeResult(int tt, int tr) {
        return scoreDao.getScoresByTestTypeAndTypeResult(tt, tr);
    }

    @Override
    public List<Score> getScoresByTestTypeAndTypeResultAndChapterId(int tt, int tr, int chapterId) {
        return scoreDao.getScoresByTestTypeAndTypeResultAndChapterId(tt, tr, chapterId);
    }

    @Override
    public List<Score> getScoresByTestTypeAndTypeResultAndLessonId(int tt, int tr, int lessonId) {
        return scoreDao.getScoresByTestTypeAndTypeResultAndLessonId(tt, tr, lessonId);
    }

    @Override
    public List<Score> getScoresByTestTypeAndTypeResultWhereLessonIdPositive(int tt, int tr) {
        return scoreDao.getScoresByTestTypeAndTypeResultWhereLessonIdPositive(tt, tr);
    }

    @Override
    public List<Score> getScoresByTestTypeAndTypeResultWhereChapterIdPositive(int tt, int tr) {
        return scoreDao.getScoresByTestTypeAndTypeResultWhereChapterIdPositive(tt, tr);
    }

    @Override
    public Score getScoreByTypeResultAndTestId(int tr, int testId) {
        return scoreDao.getScoreByTypeResultAndTestId(tr, testId);
    }

    @Override
    public List<Score> getScoresByTypeResultAndLessonIdAndPositiveTestId(int tr, int lessonId) {
        return scoreDao.getScoresByTypeResultAndLessonIdAndPositiveTestId(tr, lessonId);
    }

    @Override
    public Score getScoresByTypeResultAndLessonIdAndTestId(int tr, int lessonId, int testId) {
        return scoreDao.getScoresByTypeResultAndLessonIdAndTestId(tr, lessonId, testId);
    }

    @Override
    public Completable deleteAllScores() {
        return scoreDao.deleteAllScores();
    }

    @Override
    public Single<List<Score>> getSingleScoresByTestTypeAndTypeResult(int tt, int tr) {
        return scoreDao.getSingleScoresByTestTypeAndTypeResult(tt, tr);
    }

    @Override
    public Single<List<Score>> getSingleScoresByTestTypeAndTypeResultAndChapterId(int tt, int tr, int chapterId) {
        return scoreDao.getSingleScoresByTestTypeAndTypeResultAndChapterId(tt, tr, chapterId);
    }

    @Override
    public Single<List<Score>> getSingleScoresByTestTypeAndTypeResultAndLessonId(int tt, int tr, int lessonId) {
        return scoreDao.getSingleScoresByTestTypeAndTypeResultAndLessonId(tt, tr, lessonId);
    }

    @Override
    public Single<List<Score>> getSingleScoresByTestTypeAndTypeResultWhereLessonIdPositive(int tt, int tr) {
        return scoreDao.getSingleScoresByTestTypeAndTypeResultWhereLessonIdPositive(tt, tr);
    }

    @Override
    public Single<List<Score>> getSingleScoresByTestTypeAndTypeResultWhereChapterIdPositive(int tt, int tr) {
        return scoreDao.getSingleScoresByTestTypeAndTypeResultWhereChapterIdPositive(tt, tr);
    }

    @Override
    public Single<List<Score>> getSingleScoresByTypeResultAndTestId(int tr, int testId) {
        return scoreDao.getSingleScoresByTypeResultAndTestId(tr, testId);
    }

    @Override
    public Single<List<Score>> getSingleScoresByTypeResultAndLessonIdAndPositiveTestId(int tr, long lessonId) {
        return scoreDao.getSingleScoresByTypeResultAndLessonIdAndPositiveTestId(tr, lessonId);
    }
}

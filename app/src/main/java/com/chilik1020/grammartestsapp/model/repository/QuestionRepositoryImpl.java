package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.dao.QuestionDao;
import com.chilik1020.grammartestsapp.model.entities.Question;

import java.util.List;

import io.reactivex.Single;

public class QuestionRepositoryImpl implements QuestionReporitory {

    private QuestionDao questionDao;

    public QuestionRepositoryImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Single<List<Question>> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    @Override
    public Single<List<Question>> getFreeQuestions() {
        return questionDao.getFreeQuestions();
    }

    @Override
    public Single<List<Question>> getAllQuestionsByChapterId(long id) {
        return questionDao.getAllQuestionsByChapterId(id);
    }

    @Override
    public Single<List<Question>> getFreeQuestionByChapterId(long id) {
        return questionDao.getFreeQuestionByChapterId(id);
    }

    @Override
    public Single<List<Question>> getQuestionByTestId(long id) {
        return questionDao.getQuestionByTestId(id);
    }
}

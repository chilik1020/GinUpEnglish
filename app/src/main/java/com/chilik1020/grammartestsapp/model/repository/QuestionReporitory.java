package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.entities.Question;

import java.util.List;

import io.reactivex.Single;

public interface QuestionReporitory {

    Single<List<Question>> getAllQuestions();

    Single<List<Question>> getFreeQuestions();

    Single<List<Question>> getAllQuestionsByChapterId(long id);

    Single<List<Question>> getFreeQuestionByChapterId(long id);

    Single<List<Question>> getQuestionByTestId(long id);
}

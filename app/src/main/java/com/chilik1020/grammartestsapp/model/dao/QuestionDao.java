package com.chilik1020.grammartestsapp.model.dao;

import com.chilik1020.grammartestsapp.model.entities.Question;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

import io.reactivex.Single;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions")
    Single<List<Question>> getAllQuestions();

    @Query("SELECT questions._id, questions.lessonId, questions.testId, questions.question, questions.answer0,questions.answer1,questions.answer2,questions.answer3,questions.rightAnswer " +
            "FROM questions, tests WHERE questions.testId == tests._id AND tests.price == 0")
    Single<List<Question>> getFreeQuestions();

    @Query("SELECT questions._id, questions.lessonId, questions.testId, questions.question, questions.answer0,questions.answer1,questions.answer2,questions.answer3,questions.rightAnswer " +
            "FROM questions, tests WHERE tests.chapterId = :id AND questions.testId == tests._id")
    Single<List<Question>> getAllQuestionsByChapterId(long id);

    @Query("SELECT questions._id, questions.lessonId, questions.testId, questions.question, questions.answer0,questions.answer1,questions.answer2,questions.answer3,questions.rightAnswer " +
            "FROM questions, tests WHERE tests.chapterId = :id AND questions.testId == tests._id  AND tests.price == 0")
    Single<List<Question>> getFreeQuestionByChapterId(long id);

    @Query("SELECT * FROM questions WHERE testId = :id")
    Single<List<Question>> getQuestionByTestId(long id);
}

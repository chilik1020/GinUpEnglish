package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.dao.LessonGrammarDao;
import com.chilik1020.grammartestsapp.model.entities.LessonGrammar;

import java.util.List;

import io.reactivex.Single;

public class LessonGrammarRepositoryImpl implements LessonGrammarRepository {

    private LessonGrammarDao lessonGrammarDao;

    public LessonGrammarRepositoryImpl(LessonGrammarDao lessonGrammarDao) {
        this.lessonGrammarDao = lessonGrammarDao;
    }

    @Override
    public Single<List<LessonGrammar>> getLessonsByChapterId(long id) {
        return lessonGrammarDao.getLessonsByChapterId(id);
    }
}

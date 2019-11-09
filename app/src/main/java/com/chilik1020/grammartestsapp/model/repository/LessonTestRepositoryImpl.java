package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.dao.LessonTestDao;
import com.chilik1020.grammartestsapp.model.entities.LessonTest;

import java.util.List;

import io.reactivex.Single;

public class LessonTestRepositoryImpl implements LessonTestRepository {

    private LessonTestDao lessonTestDao;

    public LessonTestRepositoryImpl(LessonTestDao lessonTestDao) {
        this.lessonTestDao = lessonTestDao;
    }

    @Override
    public Single<List<LessonTest>> getAll() {
        return lessonTestDao.getAll();
    }

    @Override
    public Single<String> getTopicById(long id) {
        return lessonTestDao.getTopicById(id);
    }
}

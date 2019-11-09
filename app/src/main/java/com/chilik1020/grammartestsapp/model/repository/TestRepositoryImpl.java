package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.dao.TestDao;
import com.chilik1020.grammartestsapp.model.entities.Test;

import java.util.List;

import io.reactivex.Single;

public class TestRepositoryImpl implements TestRepository {

    private TestDao testDao;

    public TestRepositoryImpl(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    public Single<List<Test>> getSingleTestsByLessonId(long id) {
        return testDao.getSingleTestsByLessonId(id);
    }

    @Override
    public List<Test> getTestsByLessonId(int lessonId) {
        return testDao.getTestsByLessonId(lessonId);
    }
}

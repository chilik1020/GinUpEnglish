package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.entities.Test;

import java.util.List;

import io.reactivex.Single;

public interface TestRepository {
    Single<List<Test>> getSingleTestsByLessonId(long id);

    List<Test> getTestsByLessonId(int lessonId);
}

package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.entities.LessonTest;

import java.util.List;

import io.reactivex.Single;

public interface LessonTestRepository {

    Single<List<LessonTest>> getAll();

    Single<String> getTopicById(long id);


}

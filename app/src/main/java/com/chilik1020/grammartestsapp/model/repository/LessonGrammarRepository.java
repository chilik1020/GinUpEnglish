package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.entities.LessonGrammar;

import java.util.List;

import io.reactivex.Single;

public interface LessonGrammarRepository {

    Single<List<LessonGrammar>> getLessonsByChapterId(long id);
}

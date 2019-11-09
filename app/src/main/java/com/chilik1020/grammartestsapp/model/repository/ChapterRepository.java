package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.entities.Chapter;

import java.util.List;

import io.reactivex.Single;

public interface ChapterRepository {

    Single<List<Chapter>> getAll();

    Single<String> getChapterTopicById(long id);
}

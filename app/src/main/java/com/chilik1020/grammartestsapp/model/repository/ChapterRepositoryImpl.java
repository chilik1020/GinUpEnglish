package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.dao.ChapterDao;
import com.chilik1020.grammartestsapp.model.entities.Chapter;

import java.util.List;

import io.reactivex.Single;

public class ChapterRepositoryImpl implements ChapterRepository {

    private ChapterDao chapterDao;

    public ChapterRepositoryImpl(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }

    @Override
    public Single<List<Chapter>> getAll() {
        return this.chapterDao.getAll();
    }

    @Override
    public Single<String> getChapterTopicById(long id) {
        return this.chapterDao.getChapterTopicById(id);
    }
}

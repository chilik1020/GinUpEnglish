package com.chilik1020.grammartestsapp.data.dao;


import com.chilik1020.grammartestsapp.data.model.Chapter;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

import io.reactivex.Single;

@Dao
public interface ChapterDao {

    @Query("SELECT * FROM chapters")
    Single<List<Chapter>> getAll();

    @Query("SELECT chapter FROM chapters WHERE _id = :id")
    Single<String> getChapterTopicById(long id);
}

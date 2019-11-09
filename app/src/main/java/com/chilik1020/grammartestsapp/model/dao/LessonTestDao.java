package com.chilik1020.grammartestsapp.model.dao;

import com.chilik1020.grammartestsapp.model.entities.LessonTest;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

import io.reactivex.Single;

@Dao
public interface LessonTestDao {

    @Query("SELECT * FROM lessonsTest")
    Single<List<LessonTest>> getAll();

    @Query("SELECT topic FROM lessonsTest WHERE _id = :id")
    Single<String> getTopicById(long id);
}

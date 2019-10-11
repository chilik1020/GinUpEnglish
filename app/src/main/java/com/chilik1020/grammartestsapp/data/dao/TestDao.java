package com.chilik1020.grammartestsapp.data.dao;

import com.chilik1020.grammartestsapp.data.model.Test;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

import io.reactivex.Single;

@Dao
public interface TestDao {

    @Query("SELECT * FROM tests WHERE lessonId = :id")
    List<Test> getTestsByLessonId(long id);

    @Query("SELECT * FROM tests WHERE lessonId = :id")
    Single<List<Test>> getSingleTestsByLessonId(long id);
}

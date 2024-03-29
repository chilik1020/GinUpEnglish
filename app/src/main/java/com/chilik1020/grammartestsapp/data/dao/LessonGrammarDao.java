package com.chilik1020.grammartestsapp.data.dao;

import com.chilik1020.grammartestsapp.data.model.LessonGrammar;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

import io.reactivex.Single;

@Dao
public interface LessonGrammarDao {

    @Query("SELECT * FROM lessonsGrammar WHERE chapterId = :id")
    Single<List<LessonGrammar>> getLessonsByChapterId(long id);
}

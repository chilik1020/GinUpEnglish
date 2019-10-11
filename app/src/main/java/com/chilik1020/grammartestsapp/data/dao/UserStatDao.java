package com.chilik1020.grammartestsapp.data.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.chilik1020.grammartestsapp.data.model.UserStat;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserStatDao {

    @Update
    Completable update(UserStat userStat);

    @Query("SELECT * FROM userStats WHERE _id = :id")
    Single<UserStat> getUserStatById(Long id);
}

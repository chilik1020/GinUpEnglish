package com.chilik1020.grammartestsapp.model.dao;

import com.chilik1020.grammartestsapp.model.entities.Purchase;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PurchaseDao {

    @Update
    Completable update(Purchase p);

    @Query("SELECT * FROM purchases WHERE name = :name")
    Single<Purchase> getByName(String name);

}

package com.chilik1020.grammartestsapp.data.dao;

import com.chilik1020.grammartestsapp.data.model.Purchase;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PurchaseDao {

    @Update
    Completable update(Purchase p);

    @Query("SELECT * FROM purchases WHERE name = :name")
    Single<Purchase> getByName(String name);

}

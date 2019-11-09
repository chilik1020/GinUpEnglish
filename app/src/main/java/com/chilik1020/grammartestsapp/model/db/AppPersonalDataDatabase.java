package com.chilik1020.grammartestsapp.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.chilik1020.grammartestsapp.model.dao.PurchaseDao;
import com.chilik1020.grammartestsapp.model.dao.ScoreDao;
import com.chilik1020.grammartestsapp.model.dao.UserStatDao;
import com.chilik1020.grammartestsapp.model.entities.Purchase;
import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.model.entities.UserStat;

@Database(entities = {Purchase.class, Score.class, UserStat.class}, version = 1)
public abstract class AppPersonalDataDatabase  extends RoomDatabase {
    public abstract PurchaseDao purchaseDao();
    public abstract ScoreDao scoreDao();
    public abstract UserStatDao userStatDao();
}

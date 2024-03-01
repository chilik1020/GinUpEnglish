package com.chilik1020.grammartestsapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.chilik1020.grammartestsapp.data.dao.PurchaseDao;
import com.chilik1020.grammartestsapp.data.dao.ScoreDao;
import com.chilik1020.grammartestsapp.data.dao.UserStatDao;
import com.chilik1020.grammartestsapp.data.model.Purchase;
import com.chilik1020.grammartestsapp.data.model.Score;
import com.chilik1020.grammartestsapp.data.model.UserStat;

@Database(entities = {Purchase.class, Score.class, UserStat.class}, version = 1)
public abstract class AppPersonalDataDatabase  extends RoomDatabase {
    public abstract PurchaseDao purchaseDao();
    public abstract ScoreDao scoreDao();
    public abstract UserStatDao UserStatDao();
}

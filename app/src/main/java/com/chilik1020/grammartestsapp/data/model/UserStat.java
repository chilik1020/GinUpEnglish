package com.chilik1020.grammartestsapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userStats")
public class UserStat {
    @PrimaryKey
    @NonNull
    private int _id;

    @NonNull
    private int numberOfAppStarts;

    @NonNull
    private int statusRate;

    public UserStat(int _id, int numberOfAppStarts, int statusRate) {
        this._id = _id;
        this.numberOfAppStarts = numberOfAppStarts;
        this.statusRate = statusRate;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getNumberOfAppStarts() {
        return numberOfAppStarts;
    }

    public void setNumberOfAppStarts(int numberOfAppStarts) {
        this.numberOfAppStarts = numberOfAppStarts;
    }

    public int getStatusRate() {
        return statusRate;
    }

    public void setStatusRate(int statusRate) {
        this.statusRate = statusRate;
    }

    @Override
    public String toString() {
        return "UserStat{" +
                "_id=" + _id +
                ", numberOfAppStarts=" + numberOfAppStarts +
                ", statusRate=" + statusRate +
                '}';
    }
}

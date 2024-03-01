package com.chilik1020.grammartestsapp.data.model;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chapters")
public class Chapter implements Serializable {
    @NonNull
    @PrimaryKey
    private int _id;

    @NonNull
    private String chapter;

    public Chapter(int id, String chapter) {
        this._id = id;
        this.chapter = chapter;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
}

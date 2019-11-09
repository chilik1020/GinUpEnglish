package com.chilik1020.grammartestsapp.model.entities;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessonsTest")
public class LessonTest implements Serializable {

    @NonNull
    @PrimaryKey
    private int _id;

    @NonNull
    private int chapterId;

    @NonNull
    private String topic;

    public LessonTest(int _id, int chapterId, String topic) {
        this._id = _id;
        this.chapterId = chapterId;
        this.topic = topic;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

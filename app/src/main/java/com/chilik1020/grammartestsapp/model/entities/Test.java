package com.chilik1020.grammartestsapp.model.entities;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tests")
public class Test implements Serializable {

    @NonNull
    @PrimaryKey
    private int _id;

    @NonNull
    private int chapterId;

    @NonNull
    private int lessonId;

    @NonNull
    private String topic;

    @NonNull
    private int price;

    public Test(int _id, int chapterId, int lessonId, String topic,  int price) {
        this._id = _id;
        this.chapterId = chapterId;
        this.lessonId = lessonId;
        this.topic = topic;
        this.price = price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

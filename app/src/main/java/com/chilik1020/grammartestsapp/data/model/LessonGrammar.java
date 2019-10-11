package com.chilik1020.grammartestsapp.data.model;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessonsGrammar")
public class LessonGrammar implements Serializable {

    @NonNull
    @PrimaryKey
    private int _id;

    @NonNull
    private int chapterId;

    @NonNull
    private String topic;

    @NonNull
    private String grammar;

    public LessonGrammar(int _id, int chapterId, String topic, String grammar) {
        this._id = _id;
        this.chapterId = chapterId;
        this.topic = topic;
        this.grammar = grammar;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }


    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    @Override
    public String toString() {
        return "LessonGrammar{" +
                "_id=" + _id +
                ", chapterId=" + chapterId +
                ", topic='" + topic + '\'' +
                ", grammar='" + grammar + '\'' +
                '}';
    }
}

package com.chilik1020.grammartestsapp.data.model;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "scores")
public class Score implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long _id;

    /*
    *  0 - random test
    *  1 - chapter's test
    *  2 - lesson's test
    */
    @NonNull
    private int typeTest;

    /*
     *   0 - single result
     *   1 - medium result
     *   2 - max fot test
     */
    @NonNull
    private int typeResult;

    @NonNull
    private int chapterId;

    @NonNull
    private int lessonId;

    @NonNull
    private int testId;

    @NonNull
    private int result;

    @Ignore
    private String chapterTopic;

    @Ignore
    private String lessonTopic;

    public Score(long _id, int typeTest, int typeResult, int chapterId, int lessonId, int testId, int result) {
        this._id = _id;
        this.typeTest = typeTest;
        this.typeResult = typeResult;
        this.chapterId = chapterId;
        this.lessonId = lessonId;
        this.testId = testId;
        this.result = result;
    }

    @Ignore
    public Score(int typeTest, int typeResult, int chapterId, int lessonId, int testId, int result) {
        this._id = _id;
        this.typeTest = typeTest;
        this.typeResult = typeResult;
        this.chapterId = chapterId;
        this.lessonId = lessonId;
        this.testId = testId;
        this.result = result;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getTypeTest() {
        return typeTest;
    }

    public void setTypeTest(int typeTest) {
        this.typeTest = typeTest;
    }

    public int getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(int typeResult) {
        this.typeResult = typeResult;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setChapterTopic(String chapterTopic) {
        this.chapterTopic = chapterTopic;
    }

    public void setLessonTopic(String lessonTopic) {
        this.lessonTopic = lessonTopic;
    }

    @Override
    public String toString() {
        return "Score{" +
                "_id=" + _id +
                ", typeTest=" + typeTest +
                ", typeResult=" + typeResult +
                ", chapterId=" + chapterId +
                ", lessonId=" + lessonId +
                ", testId=" + testId +
                ", result=" + result +
                '}';
    }
}

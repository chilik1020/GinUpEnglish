package com.chilik1020.grammartestsapp.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question implements Serializable, Parcelable {

    @NonNull
    @PrimaryKey
    private int _id;

    @NonNull
    private int lessonId;

    @NonNull
    private int testId;

    private String question;

    private String answer0;
    private String answer1;
    private String answer2;
    private String answer3;

    @NonNull
    private int rightAnswer;

    @Ignore
    private int yourChoose;

    @Ignore
    private int checkedId;

    public Question(int _id, int lessonId, int testId, String question, String answer0, String answer1, String answer2, String answer3, int rightAnswer) {
        this._id = _id;
        this.lessonId = lessonId;
        this.testId = testId;
        this.question = question;
        this.answer0 = answer0;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.rightAnswer = rightAnswer;
        this.yourChoose = -1;
    }

    protected Question(Parcel in) {
        _id = in.readInt();
        lessonId = in.readInt();
        testId = in.readInt();
        question = in.readString();
        answer0 = in.readString();
        answer1 = in.readString();
        answer2 = in.readString();
        answer3 = in.readString();
        rightAnswer = in.readInt();
        yourChoose = in.readInt();
        checkedId = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer0() {
        return answer0;
    }

    public void setAnswer0(String answer0) {
        this.answer0 = answer0;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getYourChoose() {
        return yourChoose;
    }

    public void setYourChoose(int yourChoose) {
        this.yourChoose = yourChoose;
    }

    public int getCheckedId() {
        return checkedId;
    }

    public void setCheckedId(int checkedId) {
        this.checkedId = checkedId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this);
    }
}

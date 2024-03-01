package com.chilik1020.grammartestsapp.data.db;

import com.chilik1020.grammartestsapp.data.dao.ChapterDao;
import com.chilik1020.grammartestsapp.data.dao.LessonGrammarDao;
import com.chilik1020.grammartestsapp.data.dao.LessonTestDao;
import com.chilik1020.grammartestsapp.data.dao.QuestionDao;
import com.chilik1020.grammartestsapp.data.dao.TestDao;
import com.chilik1020.grammartestsapp.data.model.Chapter;
import com.chilik1020.grammartestsapp.data.model.LessonGrammar;
import com.chilik1020.grammartestsapp.data.model.LessonTest;
import com.chilik1020.grammartestsapp.data.model.Question;
import com.chilik1020.grammartestsapp.data.model.Test;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Chapter.class, LessonGrammar.class, LessonTest.class, Question.class, Test.class}, version = 1)
public abstract class AppGeneralDataDatabase extends RoomDatabase {
    public abstract ChapterDao chapterDao();
    public abstract LessonGrammarDao lessonGrammarDao();
    public abstract LessonTestDao lessonTestsDao();
    public abstract QuestionDao questionDao();
    public abstract TestDao testDao();
}

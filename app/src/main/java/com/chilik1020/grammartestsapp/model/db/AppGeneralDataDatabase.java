package com.chilik1020.grammartestsapp.model.db;

import com.chilik1020.grammartestsapp.model.dao.ChapterDao;
import com.chilik1020.grammartestsapp.model.dao.LessonGrammarDao;
import com.chilik1020.grammartestsapp.model.dao.LessonTestDao;
import com.chilik1020.grammartestsapp.model.dao.QuestionDao;
import com.chilik1020.grammartestsapp.model.dao.TestDao;
import com.chilik1020.grammartestsapp.model.entities.Chapter;
import com.chilik1020.grammartestsapp.model.entities.LessonGrammar;
import com.chilik1020.grammartestsapp.model.entities.LessonTest;
import com.chilik1020.grammartestsapp.model.entities.Question;
import com.chilik1020.grammartestsapp.model.entities.Test;

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

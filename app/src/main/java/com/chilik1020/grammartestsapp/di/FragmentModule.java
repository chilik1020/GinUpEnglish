package com.chilik1020.grammartestsapp.di;

import com.chilik1020.grammartestsapp.ui.fragments.ScoreFragment;
import com.chilik1020.grammartestsapp.ui.fragments.grammarfragments.ChaptersFragment;
import com.chilik1020.grammartestsapp.ui.fragments.grammarfragments.LessonsFragment;
import com.chilik1020.grammartestsapp.ui.fragments.testsfragments.ChaptersTestsFragment;
import com.chilik1020.grammartestsapp.ui.fragments.testsfragments.LessonTestsFragment;
import com.chilik1020.grammartestsapp.ui.fragments.testsfragments.LessonsTestsFragment;
import com.chilik1020.grammartestsapp.ui.fragments.testsfragments.Test4VarFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract ChaptersFragment contributeChapterFragment();

    @ContributesAndroidInjector
    abstract ChaptersTestsFragment contributeChaptersTestsFragment();

    @ContributesAndroidInjector
    abstract LessonsFragment contributeLessonFragment();

    @ContributesAndroidInjector
    abstract LessonsTestsFragment contributeLessonsTestsFragment();

    @ContributesAndroidInjector
    abstract LessonTestsFragment contributeLessonTestsFragment();

    @ContributesAndroidInjector
    abstract Test4VarFragment contributeTest4VarFragment();

    @ContributesAndroidInjector
    abstract ScoreFragment contributeScoreFragment();
}

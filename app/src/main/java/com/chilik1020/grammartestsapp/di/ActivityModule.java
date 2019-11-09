package com.chilik1020.grammartestsapp.di;

import com.chilik1020.grammartestsapp.ui.activities.MainActivity;
import com.chilik1020.grammartestsapp.ui.activities.PurchaseActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract PurchaseActivity contributePurchaseActivity();
}

package com.chilik1020.grammartestsapp;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.chilik1020.grammartestsapp.di.AppComponent;

import com.chilik1020.grammartestsapp.di.ApplicationModule;
import com.chilik1020.grammartestsapp.di.DaggerAppComponent;

import com.chilik1020.grammartestsapp.di.RoomModule;
import com.chilik1020.grammartestsapp.utils.AppStatusUtil;
import com.chilik1020.grammartestsapp.utils.DataBaseCopyUtil;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;

public class App extends Application implements HasActivityInjector,HasFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
    @Override
    public DispatchingAndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }

    AppComponent appComponent;

    public DataBaseCopyUtil dataBaseCopyUtil;

    @Inject
    public AppStatusUtil appStatusUtil;

    @Override
    public void onCreate() {
        super.onCreate();

        dataBaseCopyUtil = new DataBaseCopyUtil(this);
        dataBaseCopyUtil.createDBs();

        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();

        appComponent.inject(this);

        appStatusUtil.checkPurchasedProduct();

        appStatusUtil.increaseNumberOfAppStarts();
    }
}

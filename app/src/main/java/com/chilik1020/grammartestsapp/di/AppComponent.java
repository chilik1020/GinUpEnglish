package com.chilik1020.grammartestsapp.di;

import com.chilik1020.grammartestsapp.App;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApplicationModule.class,
        ContextModule.class,
        RoomModule.class,
        PresentersModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        FragmentModule.class})
public interface AppComponent {
    void inject(App mApplication);

}

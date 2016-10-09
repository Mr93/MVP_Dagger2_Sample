package com.designpattern.admin.designpattern;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prora on 10/9/2016.
 */

@Module
public class AppModule {
    Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }
}

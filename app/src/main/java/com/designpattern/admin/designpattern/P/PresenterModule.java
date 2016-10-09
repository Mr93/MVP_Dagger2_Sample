package com.designpattern.admin.designpattern.P;

import android.app.Activity;
import android.content.Context;

import com.designpattern.admin.designpattern.V.InterfaceViewForPresenter;
import com.designpattern.admin.designpattern.V.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prora on 10/9/2016.
 */

@Module
public class PresenterModule {
    private InterfacePresenterForView interfacePresenterForView;

    public PresenterModule(InterfaceViewForPresenter interfaceViewForPresenter, Activity activity){
        this.interfacePresenterForView = new DataManagerPresenter(interfaceViewForPresenter, activity);
    }

    @Provides @Singleton public InterfacePresenterForView providePresenterForView(){
        return interfacePresenterForView;
    }
}

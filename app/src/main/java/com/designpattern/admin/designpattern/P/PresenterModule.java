package com.designpattern.admin.designpattern.P;

import android.app.Activity;

import com.designpattern.admin.designpattern.V.RequiredViewOps;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prora on 10/9/2016.
 */

@Module
public class PresenterModule {
    private ProvidedPresenterOps providedPresenterOps;

    public PresenterModule(RequiredViewOps requiredViewOps, Activity activity){
        this.providedPresenterOps = new DataManagerPresenter(requiredViewOps, activity);
    }

    @Provides @Singleton public ProvidedPresenterOps providePresenterForView(){
        return providedPresenterOps;
    }
}

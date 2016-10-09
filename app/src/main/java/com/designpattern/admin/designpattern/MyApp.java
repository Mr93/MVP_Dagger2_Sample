package com.designpattern.admin.designpattern;

import android.app.Activity;
import android.app.Application;

import com.designpattern.admin.designpattern.P.PresenterModule;
import com.designpattern.admin.designpattern.V.InterfaceViewForPresenter;

/**
 * Created by prora on 10/9/2016.
 */

public class MyApp extends Application {

    private PresenterComponent presenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void createPresenterComponent(InterfaceViewForPresenter interfaceViewForPresenter, Activity activity){
        presenterComponent = DaggerPresenterComponent.builder()
                .presenterModule(new PresenterModule(interfaceViewForPresenter, activity))
                .build();
    }

    public PresenterComponent getPresenterComponent(){
        return presenterComponent;
    }
}

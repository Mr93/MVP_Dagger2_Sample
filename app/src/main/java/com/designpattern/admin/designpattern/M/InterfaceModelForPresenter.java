package com.designpattern.admin.designpattern.M;

import android.app.Activity;
import android.content.Context;

import com.designpattern.admin.designpattern.P.InterfacePresenterForModel;

/**
 * Created by mamram on 9/22/2016.
 */
public interface InterfaceModelForPresenter {
    void presenterNeedDataFromNetwork(String domain, Activity context, InterfacePresenterForModel inInterfacePresenterForModel);
}

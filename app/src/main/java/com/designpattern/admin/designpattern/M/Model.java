package com.designpattern.admin.designpattern.M;

import android.app.Activity;
import android.util.Log;

import com.designpattern.admin.designpattern.P.InterfacePresenterForModel;
import com.designpattern.admin.designpattern.M.Object.Data;
import com.designpattern.admin.designpattern.M.Strategy.DataParser;
import com.designpattern.admin.designpattern.M.Strategy.DataParserFactory;
import com.designpattern.admin.designpattern.M.Strategy.ParseDataCallback;
import com.designpattern.admin.designpattern.M.Strategy.ThethaoDataParserFactory;

import java.util.List;

/**
 * Created by mamram on 9/22/2016.
 */
public class Model implements InterfaceModelForPresenter {

    private static final String TAG = Model.class.getSimpleName();
    DataParserFactory factory;
    InterfacePresenterForModel interfacePresenterForModel;

    public Model() {
        this.factory = new ThethaoDataParserFactory();
    }

    @Override
    public void presenterNeedDataFromNetwork(String domain, Activity context, InterfacePresenterForModel inInterfacePresenterForModel) {
        try {
            this.interfacePresenterForModel = inInterfacePresenterForModel;
            DataParser dataParser = factory.createDataParser(domain, context);
            dataParser.parse(new ParseDataCallback(this));
        } catch (NullPointerException e) {
            Log.e(TAG, "presenterNeedDataFromNetwork: ", e);
        }
    }

    public void returnDataForPresenter(List<Data> dataList){
        interfacePresenterForModel.getDataNetworkFromModel(dataList);
    }
}

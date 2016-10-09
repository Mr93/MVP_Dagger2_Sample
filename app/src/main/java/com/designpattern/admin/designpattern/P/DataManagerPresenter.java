package com.designpattern.admin.designpattern.P;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.designpattern.admin.designpattern.M.InterfaceModelForPresenter;
import com.designpattern.admin.designpattern.V.InterfaceViewForPresenter;
import com.designpattern.admin.designpattern.V.MainActivity;
import com.designpattern.admin.designpattern.M.Model;
import com.designpattern.admin.designpattern.M.Object.Data;
import com.designpattern.admin.designpattern.M.Strategy.Thethao247Parser;
import com.designpattern.admin.designpattern.M.Strategy.Thethao24hParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DucDt on 9/14/2016.
 */

public class DataManagerPresenter implements InterfacePresenterForView, InterfacePresenterForModel {
	private static final List<String> domainList = new ArrayList<>(
			Arrays.asList(
					Thethao24hParser.DOMAIN,
					Thethao247Parser.DOMAIN
			)
	);
    private InterfaceModelForPresenter modelMVP;
    private InterfaceViewForPresenter viewMVP;
    private static final String TAG = DataManagerPresenter.class.getSimpleName();
    private Activity context;

	public DataManagerPresenter(InterfaceViewForPresenter interfaceViewForPresenter, Activity activity) {
		this.context = activity;
        this.modelMVP = new Model();
		this.viewMVP = interfaceViewForPresenter;
	}

	private void getDataFromDomain(String domain) {
        modelMVP.presenterNeedDataFromNetwork(domain, context, this);
	}


	public void displayNewData(List<Data> dataList){
		viewMVP.loadAdapterList(dataList);
	}

	@Override
	public void loadData(List<String> strings) {

	}

	@Override
	public void viewNeedDomainList() {
        viewMVP.getDomainList(domainList);
	}

	@Override
	public void viewNeedDataFromNetwork(List<String> domainList) {
        if (domainList != null && !domainList.isEmpty()) {
            for (String domain : domainList) {
                Log.d(TAG, "selectedStrings: " + domain);
                getDataFromDomain(domain);
            }
        } else {
            displayNewData(new ArrayList<Data>());
        }
	}

    @Override
    public void getDataNetworkFromModel(List<Data> dataList) {
        viewMVP.loadAdapterList(dataList);
    }
}

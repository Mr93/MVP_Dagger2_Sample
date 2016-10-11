package com.designpattern.admin.designpattern.P;

import android.app.Activity;
import android.util.Log;

import com.designpattern.admin.designpattern.M.ProvidedModelOps;
import com.designpattern.admin.designpattern.V.RequiredViewOps;
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

public class DataManagerPresenter implements ProvidedPresenterOps, RequiredPresenterOps {
	private static final List<String> domainList = new ArrayList<>(
			Arrays.asList(
					Thethao24hParser.DOMAIN,
					Thethao247Parser.DOMAIN
			)
	);
    private ProvidedModelOps modelMVP;
    private RequiredViewOps viewMVP;
    private static final String TAG = DataManagerPresenter.class.getSimpleName();
    private Activity context;
	private List<String> currentList = new ArrayList<>();

	public DataManagerPresenter(RequiredViewOps requiredViewOps, Activity activity) {
		this.context = activity;
        this.modelMVP = new Model();
		this.viewMVP = requiredViewOps;
		currentList.add(domainList.get(0));
	}

	private void getDataFromDomain(String domain) {
        modelMVP.presenterNeedDataFromNetwork(domain, context, this);
	}


	public void displayNewData(List<Data> dataList){
		viewMVP.loadAdapterList(dataList);
	}

	@Override
	public void getExistData() {
		List<Data> dataList = modelMVP.getExistData();
		if(dataList != null && !dataList.isEmpty()){
			Log.d(TAG, "getExistData: " + dataList.size());
			displayNewData(dataList);
		}
	}

	@Override
	public void getDomainList() {
        viewMVP.loadDomainList(domainList, currentList);
	}

	@Override
	public void getDataFromNetwork(List<String> domainList) {
        if (domainList != null && !domainList.isEmpty()) {
	        currentList = domainList;
            for (String domain : domainList) {
                Log.d(TAG, "selectedStrings: " + domain);
                getDataFromDomain(domain);
            }
        } else {
            displayNewData(new ArrayList<Data>());
        }
	}

	@Override
	public void setView(RequiredViewOps requiredViewOps, Activity activity) {
		this.context = activity;
		this.viewMVP = requiredViewOps;
	}

	@Override
    public void getDataNetworkFromModel(List<Data> dataList) {
        viewMVP.loadAdapterList(dataList);
    }
}

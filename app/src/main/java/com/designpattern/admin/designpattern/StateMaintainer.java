package com.designpattern.admin.designpattern;

import android.app.Activity;
import android.util.Log;

import com.designpattern.admin.designpattern.M.ProvidedModelOps;
import com.designpattern.admin.designpattern.P.ProvidedPresenterOps;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by prora on 10/11/2016.
 */

public class StateMaintainer {

	private final int activityId;
	protected final String TAG = getClass().getName();
	private HashMap<Integer, ProvidedPresenterOps> dataState = new HashMap<>();
	private ProvidedPresenterOps providedPresenterOps;
	private static StateMaintainer instance;

	private StateMaintainer(int activityId) {
		this.activityId = activityId;
	}

	public static StateMaintainer getInstance(int activityId){
		if(instance == null){
			instance = new StateMaintainer(activityId);
		}
		return instance;
	}

	//creates activity responsible to maintain the objects
	public boolean firstTimeIn(){
		try {
			providedPresenterOps = dataState.get(activityId);
			if(providedPresenterOps == null){
				return true;
			}else {
				return false;
			}
		}catch (NullPointerException e){
			Log.e(TAG, "firstTimeIn: ", e);
			return false;
		}
	}

	public void updateState(ProvidedPresenterOps providedPresenterOps){
		dataState.put(activityId, providedPresenterOps);
	}

	public ProvidedPresenterOps getState(){
		return providedPresenterOps;
	}



	class DataStucture{
		ProvidedPresenterOps providedPresenterOps;
		//ProvidedModelOps providedModelOps;
	}
}

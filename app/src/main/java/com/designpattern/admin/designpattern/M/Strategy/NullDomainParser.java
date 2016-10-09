package com.designpattern.admin.designpattern.M.Strategy;

import android.util.Log;
import com.designpattern.admin.designpattern.M.Object.Data;

import java.util.ArrayList;

/**
 * Created by DucDt on 9/15/2016.
 */

public class NullDomainParser implements DataParser {

	private static final String TAG = NullDomainParser.class.getSimpleName();

	private static NullDomainParser instance;

	private NullDomainParser() {

	}

	public static NullDomainParser getInstance() {
		if (instance == null) {
			instance = new NullDomainParser();
		}
		return instance;
	}

	@Override
	public void parse(ParseDataCallback callback) {
		Log.d(TAG, "parse: Null domain parse");
		callback.onParseDataDone(new ArrayList<Data>());
	}
}

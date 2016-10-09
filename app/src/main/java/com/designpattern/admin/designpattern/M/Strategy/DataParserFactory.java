package com.designpattern.admin.designpattern.M.Strategy;

import android.app.Activity;

/**
 * Created by DucDt on 9/14/2016.
 */

public interface DataParserFactory {
    DataParser createDataParser(String domain, Activity context);
}

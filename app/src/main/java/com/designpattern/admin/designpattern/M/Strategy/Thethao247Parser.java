package com.designpattern.admin.designpattern.M.Strategy;

import android.app.Activity;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;
import com.designpattern.admin.designpattern.M.Object.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DucDt on 9/14/2016.
 */

public class Thethao247Parser implements DataParser {

    private static final String TAG = Thethao247Parser.class.getSimpleName();
    private static final String URL = "http://thethao247.vn/home.rss";
    public static final String DOMAIN = "thethao247.vn";

    private static Thethao247Parser instance;

    AQuery aQuery;

    private Thethao247Parser(Activity context) {
        aQuery = new AQuery(context);

    }

    public static Thethao247Parser getInstance(Activity context) {
        if (instance == null) {
            instance = new Thethao247Parser(context);
        }
        return instance;
    }

    @Override
    public void parse(final ParseDataCallback callback) {
        aQuery.ajax(URL, XmlDom.class, new AjaxCallback<XmlDom>() {
            @Override
            public void callback(String url, XmlDom xmlDom, AjaxStatus status) {
                if (xmlDom != null) {
                    try {
	                    List<Data> lstData = new ArrayList<Data>();
                        List<XmlDom> items = xmlDom.tags("item");
                        for (int i = 0; i < items.size(); i++) {
                            XmlDom item = items.get(i);
                            Data map = new Data();
                            XmlDom title = item.child("title");
                            XmlDom link = item.child("link");
                            XmlDom image = item.child("image");
                            XmlDom pubDate = item.child("pubDate");
                            String cDataReplace = title.text();
                            String textTitle = cDataReplace.replace("<![CDATA[", "");
                            textTitle = textTitle.replace("]]>", "");
                            String textLink = link.text();
                            String textImage = image.text();
                            String textpubDate = pubDate.text();
                            map.setTitle(textTitle);
                            map.setLink(textLink);
                            map.setImage(textImage);
                            map.setPubDate(textpubDate);
                            map.setDomain(DOMAIN);
                            lstData.add(map);
                        }
	                    callback.onParseDataDone(lstData);
	                    Log.d(TAG, "callback: list data  : " + lstData.size());
                    } catch (Exception e) {
                        Log.e(TAG, "callback: ", e);
                    }
                }
            }
        });
    }
}

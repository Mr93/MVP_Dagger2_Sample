package com.designpattern.admin.designpattern.V;

import com.designpattern.admin.designpattern.M.Object.Data;

import java.util.List;

/**
 * Created by mamram on 9/21/2016.
 */
public interface InterfaceViewForPresenter {
    void getDomainList(List<String> domains);
    void loadAdapterList(List<Data> listData);
}


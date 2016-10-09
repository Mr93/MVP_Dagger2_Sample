package com.designpattern.admin.designpattern.P;

import java.util.List;

/**
 * Created by mamram on 9/21/2016.
 */
public interface InterfacePresenterForView {
    void loadData(List<String> strings);
    void viewNeedDomainList();
    void viewNeedDataFromNetwork(List<String> domainList);
}

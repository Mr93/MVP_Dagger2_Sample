package com.designpattern.admin.designpattern.V;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.designpattern.admin.designpattern.MyApp;
import com.designpattern.admin.designpattern.P.InterfacePresenterForView;
import com.designpattern.admin.designpattern.M.Object.Data;
import com.designpattern.admin.designpattern.R;
import com.guna.libmultispinner.MultiSelectionSpinner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener, InterfaceViewForPresenter {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ListView listView;
    private DataAdapter dataAdapter;
    @Inject InterfacePresenterForView presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApp) getApplication()).createPresenterComponent(this, this);
        ((MyApp) getApplication()).getPresenterComponent().inject(this);


        //setup multiple dropdown list
        setupMultipleDropdownList();

    }

    private void setupMultipleDropdownList() {
        listView = (ListView) findViewById(R.id.listView);
        presenter.viewNeedDomainList();

    }


    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        if (strings != null && !strings.isEmpty()) {
            presenter.viewNeedDataFromNetwork(strings);
        } else {
            loadAdapterList(new ArrayList<Data>());
        }
    }

    @Override
    public void getDomainList(List<String> domains) {
        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.mySpinner);
        multiSelectionSpinner.setItems(domains);
        multiSelectionSpinner.setListener(this);
        for (int i = 0; i < domains.size(); i++) {
            Log.d(TAG, "getDomainList: " + domains.get(i));
        }
    }

    @Override
    public void loadAdapterList(List<Data> listData) {
        Log.d(TAG, "loadAdapterList: ");
        dataAdapter = new DataAdapter(this, listData);
        listView.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
    }
}

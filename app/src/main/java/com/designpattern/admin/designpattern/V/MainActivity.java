package com.designpattern.admin.designpattern.V;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.designpattern.admin.designpattern.MyApp;
import com.designpattern.admin.designpattern.P.ProvidedPresenterOps;
import com.designpattern.admin.designpattern.M.Object.Data;
import com.designpattern.admin.designpattern.R;
import com.designpattern.admin.designpattern.StateMaintainer;
import com.guna.libmultispinner.MultiSelectionSpinner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener, RequiredViewOps {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ListView listView;
    private DataAdapter dataAdapter;
    @Inject
    ProvidedPresenterOps presenter;
    StateMaintainer stateMaintainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    Log.d(TAG, "onCreate: ");
	    setContentView(R.layout.activity_main);
        setupMvp();
        //setup multiple dropdown list
        setupMultipleDropdownList();
    }

    private void setupMvp(){
	    Log.d(TAG, "setupMvp: " + stateMaintainer);
	    stateMaintainer = StateMaintainer.getInstance(R.layout.activity_main);
	    if(stateMaintainer.firstTimeIn()){
	        ((MyApp) getApplication()).createPresenterComponent(this, this);
	        ((MyApp) getApplication()).getPresenterComponent().inject(this);
	        stateMaintainer.updateState(presenter);
        }else {
	        presenter = stateMaintainer.getState();
		    presenter.setView(this,this);
		    Log.d(TAG, "setupMvp: " + presenter);
	    }
    }

    private void setupMultipleDropdownList() {
        listView = (ListView) findViewById(R.id.listView);
        presenter.getDomainList();
    }

	@Override
	protected void onStart() {
		Log.d(TAG, "onStart: ");
		super.onStart();
		presenter.getExistData();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "onStop: ");
		super.onStop();
		stateMaintainer.updateState(presenter);
	}

	@Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        if (strings != null && !strings.isEmpty()) {
            presenter.getDataFromNetwork(strings);
        } else {
            loadAdapterList(new ArrayList<Data>());
        }
    }

    @Override
    public void loadDomainList(List<String> domains, List<String> selections) {
        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.mySpinner);
        multiSelectionSpinner.setItems(domains);
        multiSelectionSpinner.setListener(this);
	    multiSelectionSpinner.setSelection(selections);
        for (int i = 0; i < domains.size(); i++) {
            Log.d(TAG, "loadDomainList: " + domains.get(i));
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

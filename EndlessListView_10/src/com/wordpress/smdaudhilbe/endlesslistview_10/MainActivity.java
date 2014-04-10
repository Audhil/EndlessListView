package com.wordpress.smdaudhilbe.endlesslistview_10;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.wordpress.smdaudhilbe.endlesslistview_10.custom_component.EndLessAdapter;
import com.wordpress.smdaudhilbe.endlesslistview_10.custom_component.EndlessListView;

//	https://github.com/survivingwithandroid/Surviving-with-android - for source codessss

public class MainActivity extends Activity implements EndlessListView.EndLessListener{
	
	private static final int ITEMS_PER_REQUEST = 50;
	
	//	customized listview component
	EndlessListView eLView;
	
	//	dummy multiplicative factor
	private int mult = 1;
	
	//	custom adapter
	private EndLessAdapter adapter; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adapter = new EndLessAdapter(getApplicationContext(),creatingItems(mult),R.layout.list_row);
		
		initView();		
	}

	//	method to supply fake items to adapter
	private List<String> creatingItems(int mult) {
		
		List<String> myList = new ArrayList<String>();
		
		for (int i = 0; i < ITEMS_PER_REQUEST; i++)
			myList.add("item : "+i * mult);	

		return myList;
	}

	private void initView() {
		
		eLView = (EndlessListView)findViewById(R.id.myListView);
		
		//	note : you should add loadingView before setting adapter to the custom listview
		eLView.setLoadingView(R.layout.loading_layout);
		
		//	scrollview listener
		eLView.setListener(this);
		
		//	note : this should come next to loading view
		eLView.setAdapter(adapter);
	}

	//	endless listener - came from interface between EndlessListView and MainActivity
	@Override
	public void loadData() {
		
		mult += 10;
		
		//	new data loader
		new FakeLoader().execute();		
	}
	
	//	background thread - use this class inside fragment to avoid any orientation changes
	private class FakeLoader extends AsyncTask<Void,Void,List<String>>{

		@Override
		protected List<String> doInBackground(Void... params) {
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return creatingItems(mult);
		}
		
		@Override
		protected void onPostExecute(List<String> result) {
			super.onPostExecute(result);
			
			eLView.addNewData(result);
		}
	}
}
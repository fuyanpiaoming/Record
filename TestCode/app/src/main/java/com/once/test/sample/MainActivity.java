package com.once.test.sample;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.util.Log;

import com.once.test.R;

public class MainActivity extends Activity {
	
	private GridView testGridView;
	private TestItemAdapter testItemAdapter;
	private TestItemClass testItemClass;
	
	private ArrayList<String> testItemNamesList;
	private ArrayList<Class> testItemClassesList;
	
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		testGridView = (GridView)findViewById(R.id.test_gridview);
		
		intent = new Intent();
		
		testItemNamesList = new ArrayList<String>();
		testItemClassesList = new ArrayList<Class>();

		//定义一个TestItemClass对象
		testItemClass = new TestItemClass();
		
		testItemNamesList = testItemClass.itemNames;
		testItemClassesList = testItemClass.itemClasses;
		Log.i("lzjun","testItemNamesList="+testItemNamesList);
		Log.i("lzjun","testItemClassesList="+testItemClassesList);
		
		//定义一个TestItemAdapter对象
		testItemAdapter = new TestItemAdapter(MainActivity.this,testItemNamesList);
		
		testGridView.setAdapter(testItemAdapter);
		
        testGridView.setOnItemClickListener(new GridView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int id,
					long position) {
				// TODO Auto-generated method stub
				Log.i("lzjun","onItemClick..id=" + id + ":" +  testItemClassesList.get(id) );
				Log.i("lzjun","onItemCLick..position="+position + ":" + testItemClassesList.get((int)position));
				
				intent.setClass(MainActivity.this,testItemClassesList.get(id) );
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
				return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}


}

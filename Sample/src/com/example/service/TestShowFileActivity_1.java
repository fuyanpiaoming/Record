package com.example.service;

import java.util.List;

import com.example.sample.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

public class TestShowFileActivity_1 extends ListActivity {
	
	private List<String>items = null;
	private String path;
	protected final static int MENU_NEW = Menu.FIRST;
	protected final static int MENU_DELETE = Menu.FIRST + 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_show_file_1);
		
		Bundle bundle = this.getIntent().getExtras();
		path = bundle.getString("path");
		
		java.io.File file = new java.io.File(path);
		if (!file.exists()){
			
		}
	}

}

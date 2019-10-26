package com.once.test.sample;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class TestItemAdapter extends BaseAdapter {
	
	private ArrayList<String>itemName;
	private Context context;
	
    
    public TestItemAdapter(Context context,ArrayList<String> itemNameList){
    	itemName= itemNameList;
    	this.context = context;
    	Log.i("lzjun","itemName="+itemName);
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (itemName != null){
			return itemName.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemName.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Button button;
		if (convertView == null){
			button = new Button(context);
		}else {
			button = (Button)convertView;
		}
		
		button.setClickable(false);//如果不设置button不可点击，则button会抢夺gridview item的焦点，导致gridview item点击无效
		button.setFocusable(false);
		button.setText(itemName.get(position));

		return button;
	}
	
}

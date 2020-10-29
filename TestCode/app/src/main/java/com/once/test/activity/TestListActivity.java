package com.once.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.once.test.R;
import com.once.test.adapter.CustomListAdapter;
import com.once.test.mode.CustomMode;

import java.util.ArrayList;
import java.util.List;

public class TestListActivity extends AppCompatActivity {

    private List<CustomMode> customModeList = new ArrayList<>();
    private List<String>nameList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        initCustomList();
        initNameList();

        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(onItemClickListener);

        findViewById(R.id.btn_custom).setOnClickListener(onClickListener);
        findViewById(R.id.btn_array).setOnClickListener(onClickListener);
    }

    private void initCustomList(){
        customModeList.clear();
        customModeList.add(new CustomMode(R.drawable.fruit_apple,"Apple"));
        customModeList.add(new CustomMode(R.drawable.fruit_avocado,"Avocado"));
        customModeList.add(new CustomMode(R.drawable.fruit_banana,"Banana"));
        customModeList.add(new CustomMode(R.drawable.fruit_cherry,"Cherry"));
        customModeList.add(new CustomMode(R.drawable.fruit_coconut,"Coconut"));
        customModeList.add(new CustomMode(R.drawable.fruit_grape,"Grape"));
        customModeList.add(new CustomMode(R.drawable.fruit_lemon,"Lemon"));
        customModeList.add(new CustomMode(R.drawable.fruit_mango,"Mango"));
        customModeList.add(new CustomMode(R.drawable.fruit_coconut,"Coconut"));
        customModeList.add(new CustomMode(R.drawable.fruit_grape,"Grape"));
        customModeList.add(new CustomMode(R.drawable.fruit_lemon,"Lemon"));
        customModeList.add(new CustomMode(R.drawable.fruit_mango,"Mango"));
    }

    private void initNameList(){
        nameList.clear();
        nameList.add("Apple");
        nameList.add("Avocado");
        nameList.add("Banana");
        nameList.add("Cherry");
        nameList.add("Coconut");
        nameList.add("Grape");
        nameList.add("Lemon");
        nameList.add("Mango");
        nameList.add("Apple");
        nameList.add("Avocado");
        nameList.add("Banana");
        nameList.add("Cherry");
        nameList.add("Coconut");
        nameList.add("Grape");
        nameList.add("Lemon");
        nameList.add("Mango");
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_custom:
                    CustomListAdapter customListAdapter = new CustomListAdapter(TestListActivity.this,customModeList);
                    listView.setAdapter(customListAdapter);
                    break;
                case R.id.btn_array:
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TestListActivity.this,android.R.layout.simple_list_item_1,nameList);
                    listView.setAdapter(arrayAdapter);
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(TestListActivity.this,customModeList.get(position).getName(),Toast.LENGTH_SHORT).show();
        }
    };
}
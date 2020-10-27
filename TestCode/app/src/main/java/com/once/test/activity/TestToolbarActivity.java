package com.once.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.once.test.R;

public class TestToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_setting:
                Toast.makeText(this,"点击设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(this,"点击分享",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_data:
                Toast.makeText(this,"点击数据",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
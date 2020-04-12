package com.test.viewsample.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.test.viewsample.R;

public class SpinnerTestActivity extends AppCompatActivity {

    //下拉列表
    private Spinner spinnerDrop;
    private Spinner spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);
        spinnerDrop = findViewById(R.id.test_spinner_drop);
        ArrayAdapter<CharSequence>arrayAdapter = ArrayAdapter.createFromResource(this,R.array.items,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDrop.setAdapter(arrayAdapter);

        spinnerDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SpinnerTestActivity.this,"select item:" + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //弹出dialog
        String[] mItems = getResources().getStringArray(R.array.items);
        spinnerDialog = findViewById(R.id.test_spinner_dialog);
        ArrayAdapter<String>arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mItems);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDialog.setAdapter(arrayAdapter1);
        spinnerDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

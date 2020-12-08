package com.once.test.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.once.test.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestFileActivity extends AppCompatActivity {

    private final String TAG = TestFileActivity.class.getSimpleName();

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_file);

        textView = findViewById(R.id.tv_content);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        findViewById(R.id.btn_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData();
            }
        });

        getPath();
    }

    private void getData() {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = openFileInput("test");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            textView.setText(stringBuilder.toString());
            Log.i(TAG, "[getData]value =" + stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeData() {
        FileOutputStream fileOutputStream;
        BufferedWriter bufferedWriter = null;

        try {
            fileOutputStream = openFileOutput("test", MODE_PRIVATE);
            String data = "ni zai na?";
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getPath(){
        StringBuilder stringBuilder = new StringBuilder();
        File file = getExternalCacheDir();
        if (file != null){
            stringBuilder.append("file=").append(file.getAbsolutePath()).append("\n");
        }
        File file1 = Environment.getExternalStorageDirectory();
        if (file1 != null){
            stringBuilder.append("file1=").append(file1.getAbsolutePath()).append("\n");
        }
        File file2 = Environment.getDataDirectory();
        assert file2!= null;
        stringBuilder.append("file2=").append(file2.getAbsolutePath()).append("\n");

        File file4 = Environment.getRootDirectory();
        assert file4 != null;
        stringBuilder.append("file4").append(file4.getAbsolutePath()).append("\n");

        File file5 = Environment.getDownloadCacheDirectory();
        assert file5!= null;
        stringBuilder.append("file5=").append(file5.getAbsolutePath()).append("\n");
        textView.setText(stringBuilder.toString());
    }
}
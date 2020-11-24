package com.once.test.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.once.test.R;
import com.once.test.store.TestSqlOpenHelper;

public class TestSqlActivity extends Activity implements View.OnClickListener {

    private TestSqlOpenHelper sqlOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sql);
        //创建数据库
        sqlOpenHelper = new TestSqlOpenHelper(this, "Stu.db", null, 1);
        sqLiteDatabase = sqlOpenHelper.getWritableDatabase();

        findViewById(R.id.btn_upgrade).setOnClickListener(this);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upgrade:
                sqlOpenHelper = new TestSqlOpenHelper(this, "Stu.db", null, 2);
                sqLiteDatabase = sqlOpenHelper.getWritableDatabase();
                break;
            case R.id.btn_insert:
                ContentValues v1 = new ContentValues();
                v1.put("age", 10);
                v1.put("name", "Liu");
                sqLiteDatabase.insert("Student", null, v1);
                ContentValues v2 = new ContentValues();
                v2.put("age", 20);
                v2.put("name", "Wang");
                sqLiteDatabase.insert("Student", null, v2);
                ContentValues v3 = new ContentValues();
                v3.put("age", 40);
                v3.put("name", "Pine");
                sqLiteDatabase.insert("Student", null, v3);

                sqlOpenHelper.insert(sqLiteDatabase);
                break;
            case R.id.btn_delete:
                sqLiteDatabase.delete("Student", "age>?", new String[]{"40"});

                sqlOpenHelper.delete(sqLiteDatabase);
                break;
            case R.id.btn_update:
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("age", 18);
                sqLiteDatabase.update("Student", contentValues3, "name=?", new String[]{"Liu"});

                sqlOpenHelper.update(sqLiteDatabase);
                break;
            case R.id.btn_query:
                Cursor cursor = sqLiteDatabase.query("Student", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.i("lzj", "Student info(" + age + "," + name + ")");
                    } while (cursor.moveToNext());
                }
                cursor.close();

                sqlOpenHelper.query(sqLiteDatabase);
                break;
        }

    }
}
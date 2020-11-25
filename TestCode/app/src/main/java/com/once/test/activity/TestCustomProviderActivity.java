package com.once.test.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.once.test.R;

public class TestCustomProviderActivity extends Activity implements View.OnClickListener {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_provider);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                add();
                break;
            case R.id.btn_del:
                del();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_query:
                query();
                break;
            default:
                break;
        }
    }

    private void add() {
        Uri uri = Uri.parse("content://com.once.test.provider/book");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "China");
        contentValues.put("author", "Li");
        contentValues.put("pages", 400);
        contentValues.put("price", 25.8);
        Uri newUri = getContentResolver().insert(uri, contentValues);
        assert newUri != null;
        id = newUri.getPathSegments().get(1);
        Log.i("lzj","[add]id=" +id);
    }

    private void del() {
        Uri uri = Uri.parse("content://com.once.test.provider/book/" + id);
        Log.i("lzj","[del]uri=" + uri.toString());
        Log.i("lzj","[del]getAuthority=" + uri.getAuthority());
        Log.i("lzj","[del]getEncodedAuthority=" + uri.getEncodedAuthority());
        Log.i("lzj","[del]getEncodedPath=" + uri.getEncodedPath());
        Log.i("lzj","[del]getEncodedQuery=" + uri.getEncodedQuery());
        Log.i("lzj","[del]getEncodedUserInfo=" + uri.getEncodedUserInfo());
        Log.i("lzj","[del]getHost=" + uri.getHost());
        Log.i("lzj","[del]getPath=" + uri.getPath());
        Log.i("lzj","[del]getQuery=" + uri.getQuery());
        Log.i("lzj","[del]getScheme=" + uri.getScheme());
        Log.i("lzj","[del]getPathSegments=" + uri.getPathSegments());
        Log.i("lzj","[del]getPort=" + uri.getPort());
        getContentResolver().delete(uri, null, null);
    }

    private void update() {
        Uri uri = Uri.parse("content://com.once.test.provider/book/" + id);
        Log.i("lzj","[update]uri=" + uri.toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "HuaXia");
        contentValues.put("pages", 500);
        getContentResolver().update(uri, contentValues, null, null);
    }


    private void query() {
        Uri uri = Uri.parse("content://com.once.test.provider/book");
        Cursor cursor = getContentResolver().query(uri, null, null, null,null);
        if (cursor != null) {
            cursor.moveToFirst();
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.i("lzj", "[query]id=" + id +",name=" + name + ",author=" + author + ",pages=" + pages + ",price=" + price);
            }while(cursor.moveToNext());
        }
    }

    private void query(int id) {
        Uri uri = Uri.parse("content://com.once.test.provider/book/" + id);
        Cursor cursor = getContentResolver().query(uri, null, null, null,null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.i("lzj", "[query]name=" + name + ",author=" + author + ",pages=" + pages + ",price=" + price);
            }
        }
    }
}
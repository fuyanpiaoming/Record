package com.once.test.store;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class TestSqlOpenHelper extends SQLiteOpenHelper {

    private final String TAG = TestSqlOpenHelper.class.getSimpleName();

    private final String studentSql = "create table Student(" +
            "id integer primary key autoincrement," +
            "age integer," +
            "name text)";

    private final String courseSql = "create table Course(" +
            "cid integer primary key autoincrement," +
            "score integer," +
            "name text)";

    public TestSqlOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public TestSqlOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public TestSqlOpenHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate");
        db.execSQL(studentSql);
        db.execSQL(courseSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade");
        db.execSQL("drop table if exists Student");
        db.execSQL("drop table if exists Course");
        onCreate(db);
    }
}

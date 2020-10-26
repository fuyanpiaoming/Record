package com.once.test.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.once.test.store.MySqlOpenHelper;

public class CustomContentProvider extends ContentProvider {

    private final String TAG = CustomContentProvider.class.getSimpleName();

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    public static final String AUTHORITY = "com.once.test.provider";
    private static UriMatcher uriMatcher;
    private MySqlOpenHelper mySqlOpenHelper;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }

    public CustomContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG,"[delete]uri=" + uri);
        SQLiteDatabase sqLiteDatabase = mySqlOpenHelper.getWritableDatabase();
        int row = 0;
        Log.i(TAG,"[delete]uri match item=" + uriMatcher.match(uri));
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                row = sqLiteDatabase.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                row = sqLiteDatabase.delete("Book","id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                row = sqLiteDatabase.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                row = sqLiteDatabase.delete("Category","id=?",new String[]{categoryId});
                break;
        }
        return row;
    }

    @Override
    public String getType(Uri uri) {
        Log.i(TAG,"[getType]uri=" + uri);
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.once.test.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.once.test.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.once.test.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.once.test.provider.category";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i(TAG,"[insert]uri=" + uri);
        SQLiteDatabase sqLiteDatabase = mySqlOpenHelper.getWritableDatabase();
        Uri uriValue = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long bookId = sqLiteDatabase.insert("Book",null,values);
                uriValue = Uri.parse("content://" + AUTHORITY + "/book/" + bookId);
                Log.i(TAG,"[insert]book uriValue=" + uriValue.toString());
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long categoryId = sqLiteDatabase.insert("Category",null,values);
                uriValue = Uri.parse("content://" + AUTHORITY + "/category/" + categoryId);
                Log.i(TAG,"[insert]category uriValue=" + uriValue.toString());
                break;
        }
        return uriValue;
    }

    @Override
    public boolean onCreate() {
        Log.i(TAG,"[onCreate]");
        mySqlOpenHelper = new MySqlOpenHelper(getContext(),"BookStore.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.i(TAG,"[query]uri=" + uri);
        SQLiteDatabase sqLiteDatabase = mySqlOpenHelper.getReadableDatabase();
        Cursor cursor = null;
        Log.i(TAG,"[query]uriMatch value=" + uriMatcher.match(uri));
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = sqLiteDatabase.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                Log.i("lzj","[query]book=" +uri.getPathSegments().toString());
                cursor = sqLiteDatabase.query("Book",projection,"id=?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = sqLiteDatabase.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                Log.i("lzj","category=" +uri.getPathSegments().toString());
                cursor = sqLiteDatabase.query("Category",projection,"id=?",new String[]{categoryId},null,null,sortOrder);
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.i(TAG,"[update]uri=" + uri);
        SQLiteDatabase sqLiteDatabase = mySqlOpenHelper.getWritableDatabase();
        int row = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                row = sqLiteDatabase.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                row = sqLiteDatabase.update("Book",values,"id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                row = sqLiteDatabase.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                row = sqLiteDatabase.update("Category",values,"id=?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return row;
    }
}

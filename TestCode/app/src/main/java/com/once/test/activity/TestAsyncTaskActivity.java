package com.once.test.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.once.test.R;

public class TestAsyncTaskActivity extends Activity {

    private static final String TAG = TestAsyncTaskActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_async_task);

        new TestTask().execute();

    }


    private static class TestTask extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG,"onPreExecute");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Log.i(TAG,"onPostExecute");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG,"onProgressUpdate");
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
            Log.i(TAG,"onCancelled");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(TAG,"onCancelled");
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Log.i(TAG,"doInBackground");
            return null;
        }
    }

}
package com.once.image.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.print.PrinterId;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.once.image.R;
import java.util.ArrayList;
import java.util.List;

public class AmsActivity extends AppCompatActivity {

    private static final String TAG = AmsActivity.class.getSimpleName();

    private ActivityManager activityManager;
    private List<ActivityManager.RunningAppProcessInfo> infoList;
    private List<ActivityManager.AppTask>appTaskList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ams);
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        infoList = new ArrayList<>();
        getAppInfo();
        listView = findViewById(R.id.ams_lv);
        AmsAdapter amsAdapter = new AmsAdapter(this, infoList);
        listView.setAdapter(amsAdapter);
        listView.setOnItemClickListener(onItemClickListener);
        //app task
        getAppTasks();
    }

    private void getAppInfo() {
        infoList.clear();
        infoList.addAll(activityManager.getRunningAppProcesses());
    }

    private void getAppTasks(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            appTaskList = activityManager.getAppTasks();
            for (ActivityManager.AppTask appTask : appTaskList){
                Log.i(TAG,"getAppTasks->" + appTask.getTaskInfo().baseIntent);
            }
        }
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final String processName = infoList.get(position).processName;
            AlertDialog.Builder builder = new AlertDialog.Builder(AmsActivity.this)
                    .setTitle("任务管理")
                    .setMessage("是否关闭这个应用程序？")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG,"onItemClick processName->" + processName);
                            activityManager.killBackgroundProcesses(processName);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    private static class AmsAdapter extends BaseAdapter {

        private Context context;
        private List<ActivityManager.RunningAppProcessInfo> infoList;

        public AmsAdapter(Context context, List<ActivityManager.RunningAppProcessInfo> list) {
            this.context = context;
            this.infoList = list;
        }

        @Override
        public int getCount() {
            if (infoList != null) {
                return infoList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return infoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.ams_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = convertView.findViewById(R.id.ams_item_tv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(infoList.get(position).processName);
            return convertView;
        }

        private static class ViewHolder {
            TextView textView;
        }
    }
}
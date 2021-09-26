package com.once.image.ui.activity;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.once.image.R;

import java.util.List;


public class TelephonyManActivity extends AppCompatActivity {

    private static final String TAG = TelephonyManActivity.class.getSimpleName();

    private TelephonyManager telephonyManager;
    private SubscriptionManager subscriptionManager;
    List<SubscriptionInfo> mSubscriptions;
    private ListView listView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony_man);
        listView = findViewById(R.id.telephony_lv);
        mSubscriptions = getCurrentSubscriptions();
        myAdapter = new MyAdapter(this,mSubscriptions);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(onItemClickListener);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        subscriptionManager = SubscriptionManager.from(this);

        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_ACTIVE_DATA_SUBSCRIPTION_ID_CHANGE);
        subscriptionManager.addOnSubscriptionsChangedListener(onSubscriptionsChangedListener);

        getTelephonyInfo();
        getSubscriptionInfo();
    }


    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int subId = SubscriptionManager.INVALID_SUBSCRIPTION_ID;
            final SubscriptionInfo subscription = mSubscriptions.get(position);
            if (subscription != null) {
                subId = subscription.getSubscriptionId();
                setDefaultDataSubId(subId);
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptionManager.removeOnSubscriptionsChangedListener(onSubscriptionsChangedListener);
    }

    private PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onActiveDataSubscriptionIdChanged(int subId) {

        }
    };


    private SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener = new SubscriptionManager.OnSubscriptionsChangedListener() {
        @Override
        public void onSubscriptionsChanged() {
            mSubscriptions = getCurrentSubscriptions();
            myAdapter.notifyDataSetChanged();
        }
    };

    @SuppressLint("HardwareIds")
    private void getTelephonyInfo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //Log.i(TAG, "getTelephonyInfo -> getLine1Number:" + telephonyManager.getLine1Number());
            //Log.i(TAG, "getTelephonyInfo -> getImei:" + telephonyManager.getImei());
            Log.i(TAG, "getTelephonyInfo -> getPhoneType:" + telephonyManager.getPhoneType());
            Log.i(TAG, "getTelephonyInfo -> getPhoneCount:" + telephonyManager.getPhoneCount());
            Log.i(TAG, "getTelephonyInfo -> getNetworkOperator:" + telephonyManager.getNetworkOperator());
            Log.i(TAG, "getTelephonyInfo -> getNetworkOperatorName:" + telephonyManager.getNetworkOperatorName());
            Log.i(TAG, "getTelephonyInfo -> getCallState:" + telephonyManager.getCallState());
            Log.i(TAG, "getTelephonyInfo -> getActiveModemCount:" + telephonyManager.getActiveModemCount());
            //Log.i(TAG, "getTelephonyInfo -> getDataNetworkType:" + telephonyManager.getDataNetworkType());
            //Log.i(TAG, "getTelephonyInfo -> getSubscriberId:" + telephonyManager.getSubscriberId());
            Log.i(TAG, "getTelephonyInfo -> getSimOperatorName:" + telephonyManager.getSimOperatorName());
            //Log.i(TAG, "getTelephonyInfo -> getSimSerialNumber:" + telephonyManager.getSimSerialNumber());
            Log.i(TAG, "getTelephonyInfo -> getSimCarrierId:" + telephonyManager.getSimCarrierId());
            Log.i(TAG, "getTelephonyInfo -> getSimCarrierIdName:" + telephonyManager.getSimCarrierIdName());
            Log.i(TAG, "getTelephonyInfo -> getSimState:" + telephonyManager.getSimState());
            Log.i(TAG, "getTelephonyInfo -> getSignalStrength:" + telephonyManager.getSignalStrength());
            //Log.i(TAG, "getTelephonyInfo -> getAllCellInfo:" + telephonyManager.getAllCellInfo().toString());
        }

    }

    private void getSubscriptionInfo() {
        Log.i(TAG,"getSubscriptionInfo  Build.VERSION.SDK_INT=" + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                //return;
            }
            List<SubscriptionInfo> subscriptionInfos = subscriptionManager.getActiveSubscriptionInfoList();
            Log.i(TAG,"getSubscriptionInfo  subscriptionInfos=" + subscriptionInfos.size());
            for (SubscriptionInfo subscriptionInfo : subscriptionInfos){
                Log.i(TAG, "getSubscriptionInfo -> subscriptionInfo:" + subscriptionInfo.toString());
            }
        }
    }

    private void setDefaultDataSubId(final int subId) {
        Log.d(TAG, "setDefaultDataSubId, sub=" + subId);
        final SubscriptionManager subscriptionManager = getSystemService(SubscriptionManager.class);
        final TelephonyManager telephonyManager = getSystemService(TelephonyManager.class).createForSubscriptionId(subId);
        //subscriptionManager.setDefaultDataSubId(subId);
        telephonyManager.setDataEnabled(true);
    }

    protected List<SubscriptionInfo> getCurrentSubscriptions() {
        final SubscriptionManager manager = this.getSystemService(SubscriptionManager.class);
        return manager.getActiveSubscriptionInfoList();
    }

    private class MyAdapter extends BaseAdapter{
        private Context context;
        private List<SubscriptionInfo>infoList;
        private LayoutInflater layoutInflater;

        public MyAdapter(Context context, List<SubscriptionInfo>list){
            this.context = context;
            infoList = list;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return infoList.size();
        }

        @Override
        public Object getItem(int position) {
            return infoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.item_list,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.imageView = convertView.findViewById(R.id.telephony_iv);
                viewHolder.tvTile = convertView.findViewById(R.id.telephony_tv_title);
                viewHolder.tvSummary = convertView.findViewById(R.id.telephony_tv_summary);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final SubscriptionInfo sub = (SubscriptionInfo) getItem(position);
            if (sub != null){
                viewHolder.imageView.setImageBitmap(sub.createIconBitmap(context));
                viewHolder.tvTile.setText(sub.getDisplayName());
                String number = sub.getNumber();
                viewHolder.tvSummary.setText(TextUtils.isEmpty(number) ? "Unkonwn" : number);
            }
            return convertView;
        }

        private class ViewHolder{
            ImageView imageView;
            TextView tvTile;
            TextView tvSummary;
        }
    }

}

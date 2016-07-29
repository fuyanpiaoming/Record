package com.example.service;

import com.example.sample.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class TestTelephonyActivity extends Activity{
	
	private TextView showTxt;
	
	private boolean isSendEmail;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_telephony);
		
		showTxt = (TextView)findViewById(R.id.test_telephony_txt);
		
		exPhoneCallListener listener = new exPhoneCallListener();
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	
	public class exPhoneCallListener extends PhoneStateListener{
		
		@Override
		public void onCallStateChanged(int state, String incomingNumber){
			switch (state){
			case TelephonyManager.CALL_STATE_IDLE:
				showTxt.setText("Call state idle");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				showTxt.setText("call state offhook");
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				showTxt.setText("call state ring");
				if (isSendEmail){
					//send email
					Intent mEmaiIntent = new Intent(Intent.ACTION_SEND);
					mEmaiIntent.setType("plain/text");
					mEmaiIntent.putExtra(Intent.EXTRA_EMAIL, "Hello");
					mEmaiIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "You have an email!");
					mEmaiIntent.putExtra(Intent.EXTRA_TEXT, "Do you free in friday!");
					startActivity(Intent.createChooser(mEmaiIntent, "Make your email!"));
					
				}else{
					getContactPeople(incomingNumber);					
				}
				break;
			default:
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}
		
	}
	
	public void getContactPeople(String incomingNumber){
		
		ContentResolver cr = getContentResolver();
		Cursor cursor = null;
		String[] projection = new String[]{
				ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER
		};
		
		cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				projection, ContactsContract.CommonDataKinds.Phone.NUMBER + "=?", 
				new String[]{incomingNumber}, "");
		
		if (cursor.getCount() == 0){
			showTxt.setText("unknown number:" + incomingNumber);
		}else if (cursor.getCount() > 0){
			cursor.moveToFirst();
			String name = cursor.getString(1);
			showTxt.setText(name+ ":" + incomingNumber);
		}
	}

}

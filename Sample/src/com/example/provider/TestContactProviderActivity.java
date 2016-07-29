package com.example.provider;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sample.R;

public class TestContactProviderActivity extends Activity{
	
	private Button btn1;
	private Button btn2;
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private TextView text4;
	private TextView text5;
	
	private String strMessage;
	private static final int PICK_CONTACT_SUBACTIVITY = 2;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_contact_provider);
		
		
		btn1 = (Button)findViewById(R.id.contact_btn1);
		btn2 = (Button)findViewById(R.id.contact_btn2);
		text1 = (TextView)findViewById(R.id.contact_txt1);
		text2 = (TextView)findViewById(R.id.contact_txt2);
		text3 = (TextView)findViewById(R.id.contact_txt3);
		text4 = (TextView)findViewById(R.id.contact_txt4);
		text5 = (TextView)findViewById(R.id.contact_txt5);
		
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("content://contacts/people");
				Intent intent = new Intent(Intent.ACTION_PICK, uri);
				strMessage = text1.getText().toString();
				startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);
				
			}
		});
		
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("content://contacts/people");
				Intent intent = new Intent(Intent.ACTION_PICK,uri);
				strMessage = text2.getText().toString();
				startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);
				
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		switch(requestCode){
		case PICK_CONTACT_SUBACTIVITY:
			final Uri uriRet = data.getData();
			if (uriRet != null){
				try{
					Cursor cursor = managedQuery(uriRet, null, null, null, null);
					cursor.moveToFirst();
					String strName = "";
					String strPhone = "";
					int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
					Log.i("lzjun","contactId="+contactId);
					
					Cursor curContacts = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
					
					if (curContacts.getCount() > 0){
						curContacts.moveToFirst();
						strName = curContacts.getString(curContacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
						strPhone = curContacts.getString(curContacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						
						Log.i("lzjun","strName="+strName + ",strPhone="+strPhone);
					}else{
						//do nothing
					}
					
					String strDestAddress = strPhone;
					System.out.println(strMessage);
					
					SmsManager smsManager = SmsManager.getDefault();
					PendingIntent mPI = PendingIntent.getBroadcast(TestContactProviderActivity.this, 0, new Intent(), 0);
					
					smsManager.sendTextMessage("15618152246", null, strMessage, mPI, null);
					
					Toast.makeText(TestContactProviderActivity.this, "contact name:" + strName, Toast.LENGTH_SHORT).show();
					
					text1.setText(strName + ":" + strPhone);
				}catch(Exception e){
					text1.setText(e.toString());
					e.printStackTrace();
					
				}
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
	
	
	
	

}

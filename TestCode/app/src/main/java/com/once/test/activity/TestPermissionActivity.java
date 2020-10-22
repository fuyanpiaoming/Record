package com.once.test.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.once.test.R;

public class TestPermissionActivity extends Activity {

    private final int CALL_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_permission);
        findViewById(R.id.btn_call).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //检查权限
            if(ContextCompat.checkSelfPermission(TestPermissionActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                //申请权限
                ActivityCompat.requestPermissions(TestPermissionActivity.this,new String[]{Manifest.permission.CALL_PHONE},CALL_REQUEST_CODE);
            }else{
                call();
            }
        }
    };

    //请求权限结果回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CALL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else{
                    Toast.makeText(this,"Has no permission to call",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void call(){
        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10010"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

}
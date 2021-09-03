package com.once.image.ui.activity;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.once.image.R;



public class AlertDialogTestActivity extends Activity {

    private AlertDialog alertDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_test);
        findViewById(R.id.alert_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.alert_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.alert_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.alert_btn4).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.alert_btn1:
                    showAlertDialog();
                    break;
                case R.id.alert_btn2:
                    showAlertDialog2();
                    break;
                case R.id.alert_btn3:
                    break;
                case R.id.alert_btn4:
                    break;
            }

        }
    };

    private void showAlertDialog(){
        View v = LayoutInflater.from(this).inflate(R.layout.usb_dialog_two,null);
        View  mtpView  = v.findViewById(R.id.usb_dialog_rl_mtp);
        View ptpView = v.findViewById(R.id.usb_dialog_rl_ptp);
        View midiView = v.findViewById(R.id.usb_dialog_rl_midi);
        final ImageView imageView = v.findViewById(R.id.usb_dialog_rb_mtp);
        final ImageView imageView1 = v.findViewById(R.id.usb_dialog_rb_ptp);
        final ImageView imageView2 = v.findViewById(R.id.usb_dialog_rb_midi);
        TextView textView = v.findViewById(R.id.usb_dialog_tv);
        mtpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setSelected(true);
                if (alertDialog != null){
                    alertDialog.dismiss();
                }
            }
        });
        ptpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView1.setSelected(true);
                if (alertDialog != null){
                    alertDialog.dismiss();
                }
            }
        });
        midiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView2.setSelected(true);
                if (alertDialog != null){
                    alertDialog.dismiss();
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog = new AlertDialog.Builder(this)
                .setView(v)
                .create();
        Window window = alertDialog.getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION);
        window.setGravity(Gravity.BOTTOM);
        alertDialog.show();
    }


    private void showAlertDialog2(){
        View v = LayoutInflater.from(this).inflate(R.layout.usb_dialog_one,null);
        RadioGroup radioGroup = v.findViewById(R.id.usb_dialog_rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.usb_dialog_rb_mtp:
                        break;
                    case R.id.usb_dialog_rb_ptp:
                        break;
                    case R.id.usb_dialog_rb_midi:
                        break;
                }
            }
        });
        TextView textView = v.findViewById(R.id.usb_dialog_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog = new AlertDialog.Builder(this)
                .setView(v)
                .create();
        alertDialog.show();

        Window window = alertDialog.getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION);
        //设置Dialog显示在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置Dialog自带背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //设置Dialog阴影效果(全屏阴影效果),透明度为0.2
        WindowManager.LayoutParams lp = window.getAttributes();
        //lp.format = PixelFormat.TRANSLUCENT;
        lp.dimAmount = 0.2f;
        window.setAttributes(lp);
    }
}

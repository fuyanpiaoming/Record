package com.once.image.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.once.image.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;
    private Sensor mGravitySensor;
    private Sensor mLightSensor;
    private Sensor mProximitySensor;
    private List<Sensor>sensors;

    private TextView tvSensorType;
    private TextView tvAccelerometer;
    private TextView tvGravity;
    private TextView tvLight;
    private TextView tvProximity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        initView();
        initSensor();
        showType();
    }

    private void initView(){
        tvSensorType = findViewById(R.id.sensor_type_tv);
        tvAccelerometer = findViewById(R.id.sensor_accelerometer_tv);
        tvGravity = findViewById(R.id.sensor_gravity_tv);
        tvLight = findViewById(R.id.sensor_light_tv);
        tvProximity = findViewById(R.id.sensor_proximity_tv);
    }

    private void initSensor(){
        mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGravitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mProximitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    private void showType(){
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.sensor_type)).append(":").append("\n");
        for (Sensor sensor: sensors){
            stringBuilder.append(sensor.getType()).append(",");
        }
        tvSensorType.setText(stringBuilder.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,mAccelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorListener,mGravitySensor,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorListener,mLightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorListener,mProximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    //加速度传感器
    private SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            int type = event.sensor.getType();
            switch (type){
                case Sensor.TYPE_ACCELEROMETER:
                    showValue(tvAccelerometer,getString(R.string.sensor_accelerometer),event.values);
                    break;
                case Sensor.TYPE_GRAVITY:
                    showValue(tvGravity,getString(R.string.sensor_gravity),event.values);
                    break;
                case Sensor.TYPE_LIGHT:
                    showValue(tvLight,getString(R.string.sensor_light),event.values);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    showValue(tvProximity,getString(R.string.sensor_proximity),event.values);
                    break;
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void showValue(TextView tv,String title,float[] values){
        String stringBuilder = title + ":" + "\n" +
                "x=" + values[0] + "," +
                "y=" + values[1] + "," +
                "z=" + values[2] + "\n";
        tv.setText(stringBuilder);
    }

}
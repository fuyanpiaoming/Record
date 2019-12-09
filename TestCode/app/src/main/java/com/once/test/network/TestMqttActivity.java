package com.once.test.network;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.once.test.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class TestMqttActivity extends Activity {

    private final String TAG = "TestMqttTag";

    private MqttAndroidClient mqttAndroidClient;
    final String host = "tcp://iot.eclipse.org:1883";
    String clintId = "TestCodeClient";
    final String subscriptionTopic = "TestCodeSubscriptionTopic";
    final String publishTopic = "TestCodePublishTopic";
    final String publishMess = "Think haha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mqtt);
        initMqtt();
    }


    private void initMqtt(){
        clintId = clintId + System.currentTimeMillis();
        mqttAndroidClient = new MqttAndroidClient(getApplicationContext(), host, clintId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if (reconnect){
                    subscripeTopic();
                    Log.i(TAG,"[connectComplete]reconnect" + serverURI);
                }else{
                    Log.i(TAG,"[connectComplete]connect" + serverURI);
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.i(TAG,"[connectionLost]connection lost");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.i(TAG,"[messageArrived]topic:" + topic + ",message:" + message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.i(TAG,"deliveryComplete");
            }
        });

        final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);

        try{
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG,"[onSuccess]");
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(200);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    //订阅话题
                    subscripeTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG,"[onFailure]");
                }
            });
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    public void subscripeTopic(){
        try{
            mqttAndroidClient.subscribe(subscriptionTopic, 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG,"subscribe success");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG,"subscribe failed");
                }
            });
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    public void publishTopic(){
        try{
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(publishMess.getBytes());
            mqttAndroidClient.publish(publishTopic, mqttMessage);
            if (!mqttAndroidClient.isConnected()){
                Log.i(TAG,"[publishTopic]connect failed");
            }
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

}

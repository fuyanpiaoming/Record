package com.test.rxjavasample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

public class RxjavaChangeActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = RxjavaChangeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_change);
        findViewById(R.id.btn_map).setOnClickListener(this);
        findViewById(R.id.btn_flat_map).setOnClickListener(this);
        findViewById(R.id.btn_concat_map).setOnClickListener(this);
        findViewById(R.id.btn_buffer).setOnClickListener(this);
        findViewById(R.id.btn_scan).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_map:
                mapOperation();
                break;
            case R.id.btn_flat_map:
                flatMapOperation();
                break;
            case R.id.btn_concat_map:
                concatMapOperation();
                break;
            case R.id.btn_buffer:
                bufferOperation();
                break;
            case R.id.btn_scan:
                scanOperation();
                break;
        }
    }

    /**
     * map操作符，将被观察者发送的每个事件都用指定的函数处理，从而转换为其他类型的事件
     * 数据类型的转换
     */
    private void mapOperation(){
        Observable.just(1,2,3,4,5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Throwable {
                        Log.i(TAG,"[mapOperation]apply value=" + integer);
                        return "转换的值:" + integer;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.i(TAG,"[mapOperation]accept s=" + s);
            }
        });
    }

    /**
     * FlatMap操作符
     * 将被观察者发送的整个事件序列进行无序的转换，转换之后的事件序列与原序列不同
     */
    private void flatMapOperation() {
        Observable.just(1, 2, 3, 4, 5)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Throwable {
                        Log.i(TAG,"[flatMapOperation]apply value=" + integer);
                        List<String>list = new ArrayList<>();
                        list.add("event1:" + integer);
                        list.add("event2:" + integer);
                        list.add("event3:" + integer);
                        return Observable.fromIterable(list);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.i(TAG,"[flatMapOperation]accept s=" + s);
            }
        });
    }

    /**
     * concatMap操作符
     * 将被观察者发送的整个事件序列进行有序的转换，转换之后的事件序列与原序列相同
     */
    private void concatMapOperation(){
        Observable.just(1,2,3,4,5)
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Throwable {
                        Log.i(TAG,"[concatMapOperation]apply value=" + integer);
                        List<String>list = new ArrayList<>();
                        list.add("event1:" + integer);
                        list.add("event2:" + integer);
                        return Observable.fromIterable(list);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.i(TAG,"[concatMapOperation]accept s=" + s);
            }
        });
    }

    /**
     * buffer操作符：定期从被观察者发送的事件序列中取一定数量的事件放到缓存中并且发送给观察者
     */
    private void bufferOperation(){
        Observable.just(1,2,3,4,5)
                .buffer(3,1)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[bufferOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull List<Integer> integers) {
                        for(Integer i: integers){
                            Log.i(TAG,"[bufferOperation]onNext value=" + i);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[bufferOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[bufferOperation]onComplete");
                    }
                });
    }

    /**
     * Scan操作符
     */
    private void scanOperation(){
        Observable.just(1,2,3,4,5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer sum, Integer item) throws Throwable {
                        Log.i(TAG,"[scanOperation]apply sum=" + sum + ",item=" + item);
                        return sum + item;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.i(TAG,"[scanOperation]accept sum=" + integer);
            }
        });

        Observable.just(1,2,3,4,5)
                .scan(2, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer sum, Integer item) throws Throwable {
                        Log.i(TAG,"[scanOperation]apply1 sum=" + sum + ",item=" + item);
                        return sum+item;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.i(TAG,"[scanOperation]accept1 sum=" + integer);
            }
        });
    }


}

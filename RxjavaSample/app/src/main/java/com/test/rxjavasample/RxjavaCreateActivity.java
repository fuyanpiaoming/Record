package com.test.rxjavasample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromCallable;
import io.reactivex.rxjava3.subjects.Subject;

public class RxjavaCreateActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = RxjavaCreateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_create);
        //设置监听器
        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_just).setOnClickListener(this);
        findViewById(R.id.btn_fromarray).setOnClickListener(this);
        findViewById(R.id.btn_fromIterable).setOnClickListener(this);
        findViewById(R.id.btn_defer).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
        findViewById(R.id.btn_interval).setOnClickListener(this);
        findViewById(R.id.btn_intervalrange).setOnClickListener(this);
        findViewById(R.id.btn_range).setOnClickListener(this);
        findViewById(R.id.btn_range_long).setOnClickListener(this);
        findViewById(R.id.btn_from).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                createOperation();
                break;
            case R.id.btn_just:
                justOperation();
                break;
            case R.id.btn_fromarray:
                fromArrayOperation();
                break;
            case R.id.btn_fromIterable:
                fromIterableOperation();
                break;
            case R.id.btn_defer:
                deferOperation();
                break;
            case R.id.btn_time:
                timeOperation();
                break;
            case R.id.btn_interval:
                intervalOperation();
                break;
            case R.id.btn_intervalrange:
                intervalRangeOperation();
                break;
            case R.id.btn_range:
                rangeOperation();
                break;
            case R.id.btn_range_long:
                rangeLongOperation();
                break;
            case R.id.btn_from:
                fromOperation();
                break;
        }

    }

    /**
     * create操作符：创建被观察者对象
     */
    private void createOperation() {
        //创建被观察者对象Observable:发送事件
        Observable<String>observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                //ObservableEmitter事件发送器，被观察者在该方法中发送事件给观察者
                Log.i(TAG,"[createOperation]subscribe");
                emitter.onNext("one");
                emitter.onNext("two");
                emitter.onComplete();
            }
        });

        //通过Observer接口创建观察者对象Observer:接收事件并处理
        Observer<String>observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"[createOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"[createOperation]onNext s=" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"[createOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"[createOperation]onComplete");
            }
        };

        //建立观察者和被观察者之间的订阅关系
        observable.subscribe(observer);
    }

    /**
     * just操作符：相当于创建了一个Observable对象并执行onNext("one"),onNext("two"),onNext("three")
     */
    private void justOperation(){
        Observable.just("one","two","three")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[justOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.i(TAG,"[justOperation]onNext s=" + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[justOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[justOperation]onComplete");
                    }
                });
    }

    /**
     * fromArray操作符,通过数组创建Observable对象，并发送数组元素给观察者对象处理
     */
    private void fromArrayOperation(){
        String[] names = {"zhao","qian","sun","li"};
        Observable.fromArray(names)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[fromArrayOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.i(TAG,"[fromArrayOperation]onNext s=" + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[fromArrayOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[fromArrayOperation]onComplete");
                    }
                });
    }

    /**
     * fromIterable操作符：通过集合创建Observable对象，并将集合中的元素依次发给观察者处理
     */
    private void fromIterableOperation(){
        List<String> nameList = new ArrayList<>();
        nameList.add("shi");
        nameList.add("ci");
        nameList.add("ge");
        Observable.fromIterable(nameList)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[fromIterable]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.i(TAG,"[fromIterable]onNext s=" + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[fromIterable]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[fromIterable]onComplete");
                    }
                });
    }

    /**
     * empty操作符：创建的Observable仅发送onComplete事件
     */
    private void emptyOperation(){
        Log.i(TAG,"[emptyOperation]");
        Observable observable = Observable.empty();
    }

    /**
     * error操作符：创建的Observable仅发送onError事件
     */
    private void errorOperation(){
        Log.i(TAG,"[errorOperation]");
        Observable observable = Observable.error(new RuntimeException());
    }

    /**
     * never操作符：创建的Observable对象什么都不发送
     */
    private void neverOperation(){
        Log.i(TAG,"[neverOperation]");
        Observable observable = Observable.never();
    }

    /**
     * defer操作符：直到有观察者订阅被观察者时，才会动态创建被观察者对象，并发送事件
     * Observable.subscribe(observer)语句执行时创建Observable对象
     */
    private void deferOperation(){
        Observable.defer(new Supplier<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> get() throws Throwable {
                Log.i(TAG,"[deferOperation]get");
                //SystemClock.sleep(5000);
                return Observable.just("one","two","three");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"[deferOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"[deferOperation]onNext S=" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"[deferOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"[deferOperation]onComplete");
            }
        });
    }

    /**
     * time操作符：创建一个被观察者对象，延迟规定时间之后，发送一个数值0
     * 如下：延迟2秒调用onNext(0)发送事件0给观察者处理
     */
    private void timeOperation(){
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[timeOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Long value) {
                        Log.i(TAG,"[timeOperation]onNext value=" + value);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[timeOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[timeOperation]onComplete");
                    }
                });
    }

    /**
     * interval操作符：创建一个被观察者对象，每隔2秒发送一个事件，事件从0开始，不断叠加
     */
    private void intervalOperation(){
        final Disposable[] disposable = new Disposable[1];
        Observable.interval(2,TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[intervalOperation]onSubscribe");
                        disposable[0] = d;
                    }

                    @Override
                    public void onNext(@NonNull Long value) {
                        Log.i(TAG,"[intervalOperation]onNext value=" + value);
                        //如果事件value值大于20，则让被观察者停止发送事件
                        if (value > 20) {
                            if (disposable[0] != null && !disposable[0].isDisposed()) {
                                disposable[0].dispose();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[intervalOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[intervalOperation]onComplete");
                    }
                });
    }

    /**
     * intervalRange操作符：创建一个被观察者对象，每隔指定事件就发送事件，事件总数可以指定
     * 起始事件：2
     * 事件总数：5
     * 第一次发送事件前延迟时间：3
     * 每次发送事件时间间隔：2
     * 事件单位：秒
     */
    private void intervalRangeOperation(){
        Observable.intervalRange(2,5,3,2,TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[intervalRangeOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Long value) {
                        Log.i(TAG,"[intervalRangeOperation]onNext value=" + value);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[intervalRangeOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[intervalRangeOperation]onComplete");
                    }
                });
    }

    /**
     * range操作符：创建一个被观察者对象，连续发送一个指定范围的事件序列,支持整型数据
     */
    private void rangeOperation(){
        //创建一个Observable对象之后，从5开始，连续发送10个
        Observable.range(5,10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[rangeOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.i(TAG,"[rangeOperation]onNext value=" + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[rangeOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[rangeOperation]onComplete");
                    }
                });
    }

    /**
     * rangeLong操作符：创建一个被观察者对象，连续发送一个指定范围的事件序列,支持长整型数据
     */
    private void rangeLongOperation(){
        Observable.rangeLong(100,150)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[rangeLongOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Log.i(TAG,"[rangeLongOperation]onNext value=" + aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[rangeLongOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[rangeLongOperation]onComplete");
                    }
                });
    }

    /**
     * from操作符
     */
    private void fromOperation(){
        Integer[] items = { 0, 1, 2, 3, 4, 5 };
        Observable.fromArray(items)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.i(TAG,"[fromOperation]accept value=" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.i(TAG,"[fromOperation]accept error");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.i(TAG,"[fromOperation]onComplete");
                    }
                });
    }

}

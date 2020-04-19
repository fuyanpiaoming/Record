package com.test.rxjavasample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromCallable;

public class RxjavaMergeActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = RxjavaMergeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_merge);
        findViewById(R.id.btn_merge).setOnClickListener(this);
        findViewById(R.id.btn_merge_array).setOnClickListener(this);
        findViewById(R.id.btn_concat).setOnClickListener(this);
        findViewById(R.id.btn_concat_array).setOnClickListener(this);
        findViewById(R.id.btn_merge_delay_error).setOnClickListener(this);
        findViewById(R.id.btn_concat_delay_error).setOnClickListener(this);
        findViewById(R.id.btn_zip).setOnClickListener(this);
        findViewById(R.id.btn_combineLatest).setOnClickListener(this);
        findViewById(R.id.btn_combineLatest_delayerror).setOnClickListener(this);
        findViewById(R.id.btn_reduce).setOnClickListener(this);
        findViewById(R.id.btn_collect).setOnClickListener(this);
        findViewById(R.id.btn_startwith).setOnClickListener(this);
        findViewById(R.id.btn_startWithArray).setOnClickListener(this);
        findViewById(R.id.btn_count).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_merge:
                mergeOperation();
                break;
            case R.id.btn_merge_array:
                mergeArrayOperation();
                break;
            case R.id.btn_concat:
                concatOperation();
                break;
            case R.id.btn_concat_array:
                concatArrayOperation();
                break;
            case R.id.btn_merge_delay_error:
                mergeArrayDelayErrorOperation();
                break;
            case R.id.btn_concat_delay_error:
                concatArrayDelayErrorOperation();
                break;
            case R.id.btn_combineLatest:
                combineLatestOperation();
                break;
            case R.id.btn_zip:
                zipOperation();
                break;
            case R.id.btn_combineLatest_delayerror:
                break;
            case R.id.btn_reduce:
                reduceOperation();
                break;
            case R.id.btn_collect:
                collectOperation();
                break;
            case R.id.btn_startwith:
                startWithOperation();
                break;
            case R.id.btn_startWithArray:
                startWithArrayOperation();
                break;
            case R.id.btn_count:
                countOperation();
                break;
        }

    }

    /**
     * merge操作符：组合多个被观察者一起发送数据，合并后按时间线并行执行
     * 组合的被观察者数量要小于等于4个(数量<=4)
     */
    private void mergeOperation() {
        Observable.merge(
                Observable.intervalRange(0, 3, 1, 2, TimeUnit.SECONDS),
                Observable.intervalRange(2, 3, 1, 2, TimeUnit.SECONDS),
                Observable.intervalRange(4, 3, 1, 2, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "[megerOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Long value) {
                        Log.i(TAG, "[megerOperation]onNext value=" + value);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "[megerOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "[megerOperation]onComplete");
                    }
                });
    }

    /**
     * mergeArray操作符：组合多个（数量要大于4）被观察者一起发送数据，合并后按时间线并行执行
     */
    private void mergeArrayOperation() {
        Observable.mergeArray(
                Observable.intervalRange(0, 3, 1, 2, TimeUnit.SECONDS),
                Observable.intervalRange(3, 3, 1, 2, TimeUnit.SECONDS),
                Observable.intervalRange(6, 3, 1, 2, TimeUnit.SECONDS),
                Observable.intervalRange(9, 3, 1, 2, TimeUnit.SECONDS),
                Observable.intervalRange(12, 3, 1, 2, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "[mergeArrayOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Long value) {
                        Log.i(TAG, "[mergeArrayOperation]onNext value=" + value);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "[mergeArrayOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "[mergeArrayOperation]onComplete");
                    }
                });
    }

    /**
     * concat操作符：组合多个（数量小于等于4）被观察者一起发送数据，事件序列串行发送
     */
    private void concatOperation() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.i(TAG, "[concatOperation]accept value=" + integer);
                    }
                });
    }

    /**
     * concatArray操作符：组合5个或者5个以上的被观察者一起发送数据，串行发送数据
     */
    private void concatArrayOperation() {
        Observable.concatArray(Observable.just(1, 2), Observable.just(3, 4), Observable.just(5, 6), Observable.just(7, 8), Observable.just(9, 10))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.i(TAG, "[concatArrayOperation]accept value=" + integer);
                    }
                });
    }

    /**
     *concatArrayDelayError操作符:组合多个被观察者一起发送数据时，如果某个被观察者出现错误时，会延迟该被观察者发送onError事件，直到
     * 所有的被观察者全部发送完数据后，才发送onError事件给观察者
     */
    private void concatArrayDelayErrorOperation(){
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                        Log.i(TAG,"[concatArrayDelayErrorOperation]subscribe");
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new Throwable());
                    }
                }),
                Observable.just(4,5,6)
        ).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"[concatArrayDelayErrorOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer value) {
                Log.i(TAG,"[concatArrayDelayErrorOperation]onNext value=" + value);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"[concatArrayDelayErrorOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"[concatArrayDelayErrorOperation]onComplete");
            }
        });
    }


    /**
     *mergeArrayDelayError操作符:组合多个被观察者一起发送数据时，如果某个被观察者出现错误时，会延迟该被观察者发送onError事件，直到
     * 所有的被观察者全部发送完数据后，才发送onError事件给观察者
     */
    private void mergeArrayDelayErrorOperation(){
        Observable.mergeArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                        Log.i(TAG,"[concatArrayDelayErrorOperation]subscribe");
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new Throwable());
                    }
                }),
                Observable.just(4,5,6)
        ).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"[concatArrayDelayErrorOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer value) {
                Log.i(TAG,"[concatArrayDelayErrorOperation]onNext value=" + value);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"[concatArrayDelayErrorOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"[concatArrayDelayErrorOperation]onComplete");
            }
        });
    }

    /**
     * zip操作符:合并多个被观察者发送的事件，生成一个新的事件序列。按照被观察者发送事件序列的顺序，进行一一对应的合并
     * 合并事件的数量与多个被观察者中事件数量最少的那个相同
     */
    private void zipOperation(){
        Observable.zip(Observable.just(1, 2, 3), Observable.just("a", "b", "c","d"), new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Throwable {
                Log.i(TAG,"[zipOperation]apply integer=" + integer + ",s=" + s);
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.i(TAG,"[zipOperation]accept s=" + s);
            }
        });
    }

    /**
     * combineLatest操作符当两个Observables中的任何一个发送了数据后，将先发送了数据的Observables的最新（最后）一个数据与
     * 另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据
     */
    private void combineLatestOperation() {
        Observable.combineLatest(Observable.just(1, 2, 3), Observable.intervalRange(4, 3, 1, 1, TimeUnit.SECONDS),
                new BiFunction<Integer, Long, Long>() {
                    @Override
                    public Long apply(Integer integer, Long aLong) throws Throwable {
                        Log.i(TAG,"[combineLatestOperation]apply integer=" + integer + ",aLong=" + aLong);
                        return integer + aLong;
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                Log.i(TAG,"[combineLatestOperation]accept value=" + aLong);
            }
        });
    }

    /**
     * reduce操作符：把被观察者需要发送的事件聚合成一个事件进行发送
     */
    private void reduceOperation(){
        Observable.just(1,2,3,4,5)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Throwable {
                        Log.i(TAG,"[reduceOperation]apply v1=" + integer + ",v2=" + integer2);
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.i(TAG,"[reduceOperation]accept value=" + integer);
            }
        });
    }

    /**
     * collect操作符：将被观察者发送的事件放到一个数据结构中
     */
    private void collectOperation(){
        Observable.just(1,2,3,4,5)
                .collect(new Supplier<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> get() throws Throwable {
                        Log.i(TAG,"[collectOperation]create arraylist");
                        return new ArrayList<Integer>();
                    }
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> list, Integer integer) throws Throwable {
                        Log.i(TAG,"[collectOperation]BiConsumer accept integer=" + integer);
                        list.add(integer);
                    }
                }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> list) throws Throwable {
                Log.i(TAG,"[collectOperation]accept list=" + list);
            }
        });
    }


    /**
     * startWith操作符：被观察者发送事件前，追加发送一些数据
     */
    private void startWithOperation(){
        Observable.just(1,2,3)
                .startWith(Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.i(TAG,"[startWithOperation]accept value=" + integer);
                    }
                });
    }

    /**
     * startWithArray操作符：被观察者发送事件前，追加发送一些数据
     */
    private void startWithArrayOperation(){
        Observable.just(1,2,3)
                .startWithArray(5,6,7)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.i(TAG,"[startWithArrayOperation]accept value=" + integer);
                    }
                });
    }

    /**
     * count操作符：计算被观察者发送事件的总数
     */
    private void countOperation(){
        Observable.just(1,2,3,4,5)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        Log.i(TAG,"[countOperation]accept count=" + aLong);
                    }
                });
    }

}

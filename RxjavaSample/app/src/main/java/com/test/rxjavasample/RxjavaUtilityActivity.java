package com.test.rxjavasample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiPredicate;
import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;

public class RxjavaUtilityActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = RxjavaUtilityActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_utility);
        findViewById(R.id.btn_delay).setOnClickListener(this);
        findViewById(R.id.btn_do).setOnClickListener(this);
        findViewById(R.id.btn_onErrorReturn).setOnClickListener(this);
        findViewById(R.id.btn_onErrorResumeNext).setOnClickListener(this);
        findViewById(R.id.btn_retry).setOnClickListener(this);
        findViewById(R.id.btn_retry_until).setOnClickListener(this);
        findViewById(R.id.btn_retry_when).setOnClickListener(this);
        findViewById(R.id.btn_repeat).setOnClickListener(this);
        findViewById(R.id.btn_repeat_when).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delay:
                delayOperation();
                break;
            case R.id.btn_do:
                doOperation();
                break;
            case R.id.btn_onErrorReturn:
                onErrorReturnOperation();
                break;
            case R.id.btn_onErrorResumeNext:
                onErrorResumeNextOperation();
                break;
            case R.id.btn_retry:
                retryOperation();
                break;
            case R.id.btn_retry_until:
                retryUntilOperation();
                break;
            case R.id.btn_retry_when:
                retryWhenOperation();
                break;
            case R.id.btn_repeat:
                repeatOperation();
                break;
            case R.id.btn_repeat_when:
                repeatWhenOperation();
                break;
        }
    }

    /**
     * delay操作符：延迟被观察者开始发送事件的时间
     */
    private void delayOperation() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.i(TAG, "[delayOperation]subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).delay(5, TimeUnit.SECONDS)//延迟5s后被观察者开始发送事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "[delayOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.i(TAG, "[delayOperation]onNext value=" + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "[delayOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "[delayOperation]onComplete");
                    }
                });
    }

    /**
     * do操作符：在事件的生命周期中调用
     */
    private void doOperation() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                //onError和onComplete事件只能调用一个，有且仅能出现一个
                Log.i(TAG, "[doOperation]subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                //emitter.onError(new Throwable());
                emitter.onComplete();
            }
        }).doOnEach(new Consumer<Notification<Integer>>() {//被观察者每次发送一个事件调用一次doOnEach
            @Override
            public void accept(Notification<Integer> integerNotification) throws Throwable {
                Log.i(TAG, "[doOperation]doOnEach accept");
            }
        }).doOnSubscribe(new Consumer<Disposable>() {//订阅时调用
            @Override
            public void accept(Disposable disposable) throws Throwable {
                Log.i(TAG, "[doOperation]doOnSubscribe accept");
            }
        }).doOnNext(new Consumer<Integer>() {//执行next事件前调用
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.i(TAG, "[doOperation]doOnNext accept");
            }
        }).doAfterNext(new Consumer<Integer>() {//执行完next事件后调用
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.i(TAG, "[doOperation]doAfterNext accept");
            }
        }).doOnError(new Consumer<Throwable>() {//被观察者发送错误事件时调用
            @Override
            public void accept(Throwable throwable) throws Throwable {
                Log.i(TAG, "[doOperation]doOnError accept");
            }
        }).doOnComplete(new Action() {//被观察者正常发送事件结束后调用
            @Override
            public void run() throws Throwable {
                Log.i(TAG, "[doOperation]doOnComplete run");
            }
        }).doAfterTerminate(new Action() {//被观察者发送事件完毕后调用，无论是正常发送结束还是异常终止
            @Override
            public void run() throws Throwable {
                Log.i(TAG, "[doOperation]doAfterTerminate run");
            }
        }).doFinally(new Action() {//最后执行
            @Override
            public void run() throws Throwable {
                Log.i(TAG, "[doOperation]doFinally run");
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Throwable {
                Log.i(TAG, "[doOperation]doOnDispose run");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "[doOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(TAG, "[doOperation]onNext value=" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "[doOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "[doOperation]onComplete");
            }
        });
    }

    /**
     * onErrorReturn操作符:遇到错误时,发送一个特殊事件正常终止
     */
    private void onErrorReturnOperation() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.i(TAG, "[onErrorReturnOperation]subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Throwable("Error"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Throwable {
                //捕获异常并处理
                Log.i(TAG, "[onErrorReturnOperation]apply throwable=" + throwable.toString());
                return 100;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "[onErrorReturnOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(TAG, "[onErrorReturnOperation]onNext integer=" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "[onErrorReturnOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "[onErrorReturnOperation]onComplete");
            }
        });
    }

    /**
     * onErrorResumeNext操作符：遇到错误时，创建一个新的Observable发送数据
     */
    private void onErrorResumeNextOperation() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.i(TAG, "[onErrorResumeNextOperation]subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Throwable("Error"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Throwable {
                //捕获异常
                Log.i(TAG, "[onErrorResumeNextOperation]onErrorResumeNext throwable=" + throwable.toString());
                return Observable.just(4, 5, 6);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "[onErrorResumeNextOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(TAG, "[onErrorResumeNextOperation]onNext value=" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "[onErrorResumeNextOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "[onErrorResumeNextOperation]onComplete");
            }
        });
    }

    /**
     * retry操作符：发生错误时，让被观察者重新发送事件
     * retry():出现错误时，让被观察者重新发送事件
     * retry(5)：出现错误时，让被观察者重新发送事件，重复次数5次
     */
    private void retryOperation() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.i(TAG,"[retryOperation]subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Throwable("error"));
            }
        }).retry(new Predicate<Throwable>() {//拦截错误后，判断是否需要重新发送请求
            @Override
            public boolean test(Throwable throwable) throws Throwable {
                Log.i(TAG,"[retryOperation]retry test");
                //返回false表示不重新重新发送数据并且调用观察者的onError结束
                //返回true表示重新发送请求（若持续遇到错误，就持续重新发送）
                return true;
            }
        }).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"[retryOperation]onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.i(TAG,"[retryOperation]onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"[retryOperation]onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"[retryOperation]onComplete");
                    }
                });
    }

    /**
     * retry操作符：发生错误时，让被观察者重新发送事件
     */
    private void retryOperation1(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.i(TAG,"[retryOperation1]subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Throwable("error"));
            }
        }).retry(new BiPredicate<Integer, Throwable>() {
            @Override
            public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Throwable {
                Log.i(TAG,"[retryOperation1]test retry time:" + integer);
                //返回false表示不重新重新发送数据且调用观察者的onError结束
                //返回true表示重新发送请求（若持续遇到错误，就持续重新发送）
                return true;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"[retryOperation1]onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(TAG,"[retryOperation1]onNext value=" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"[retryOperation1]onError e:" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"[retryOperation1]onComplete");
            }
        });
    }

    /**
     * retryUntil操作符：出现错误时，让被观察者重新发送数据
     */
    private void retryUntilOperation(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                Log.i(TAG,"[retryUntilOperation]subscribe");
                emitter.onNext("a");
                emitter.onNext("b");
                emitter.onNext("c");
                emitter.onError(new Throwable());
            }
        }).retryUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Throwable {
                Log.i(TAG,"[retryUntilOperation]retryUntil");
                return false;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"[retryUntilOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"[retryUntilOperation]onNext S=" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"[retryUntilOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"[retryUntilOperation]onComplete");
            }
        });
    }

    /**
     * retryWhen操作符：遇到错误时，将发生的错误传递给一个新的被观察者（Observable），并决定是否需要重新订阅原始被观察者且发送事件
     */
    private void retryWhenOperation(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                Log.i(TAG,"[retryWhenOperation]subscribe");
                emitter.onNext("a");
                emitter.onNext("b");
                emitter.onNext("c");
                emitter.onError(new Throwable("Error"));
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Throwable {
                Log.i(TAG,"[retryWhenOperation]retryWhen apply1");
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                        //在该方法中捕获异常信息

                        //新的被观察者 Observable发送的事件为Error事件，那么原始Observable则不重新发送事件
                        //新的被观察者 Observable发送的事件为Next事件 ，那么原始的Observable则重新发送事件
                        return Observable.error(throwable);
                        //return Observable.just(1);
                    }
                });
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"[retryWhenOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"[retryWhenOperation]onNext s=" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"[retryWhenOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"[retryWhenOperation]onComplete");
            }
        });
    }

    /**
     * repeat操作符：让被观察者重复发送事件
     */
    private void repeatOperation(){
        Observable.just("a","b","c")
                .repeat()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.i(TAG,"[repeatOperation]accept s = " + s);
                    }
                });
    }

    /**
     * repeatWhen操作符:被观察者有条件的重复发送事件
     */
    private void repeatWhenOperation(){
        Observable.just(1,2,3,4,5)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Throwable {
                        return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Object o) throws Throwable {
                                Log.i(TAG,"[repeatWhenOperation]repeatWhen apply");
                                int i = 1;
                                if ( i ==  1){
                                    //新的被观察者发送onComplete事件，表示不重新订阅原来的Observable且不发送数据
                                    return Observable.empty();
                                }else if (i == 2){
                                    //新的被观察者发送onError事件，表示不重新订阅原来的Observable且不发送数据
                                    return Observable.error(new Throwable());
                                }else{
                                    //新的被观察者发送onNext事件，表示重新订阅原来的Observable并且发送数据
                                    return Observable.just(1,2);
                                }
                            }
                        });
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"[repeatWhenOperation]onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(TAG,"[repeatWhenOperation]onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"[repeatWhenOperation]onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"[repeatWhenOperation]onComplete");
            }
        });
    }


}

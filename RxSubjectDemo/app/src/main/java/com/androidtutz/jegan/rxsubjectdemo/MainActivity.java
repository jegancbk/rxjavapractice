package com.androidtutz.jegan.rxsubjectdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asyncSubjectDemo1();
        //asyncSubjectDemo2();
        //behaviorSubjectDemo1();
        //behaviorSubjectDemo2();
        //publishSubjectDemo1();
        //publishSubjectDemo2();
        //replaySubjectDemo1();
        replaySubjectDemo2();



    }

    void asyncSubjectDemo1() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "Javascript", "HTML");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        observable.subscribe(asyncSubject);

        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.subscribe(getThirdObserver());

    }

    void asyncSubjectDemo2() {

        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(getFirstObserver());

        asyncSubject.onNext("Java");
        asyncSubject.onNext("Kotlin");
        asyncSubject.onNext("Javascript");
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.onNext("Html");
        asyncSubject.onComplete();
        asyncSubject.subscribe(getThirdObserver());

    }

    void behaviorSubjectDemo1() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "Javascript", "HTML")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        BehaviorSubject<String> behaviorSubject= BehaviorSubject.create();
        observable.subscribe(behaviorSubject);

        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.subscribe(getSecondObserver());
        behaviorSubject.subscribe(getThirdObserver());

    }

    void behaviorSubjectDemo2() {


        BehaviorSubject<String> behaviorSubject= BehaviorSubject.create();

        behaviorSubject.subscribe(getFirstObserver());

        behaviorSubject.onNext("Java");
        behaviorSubject.onNext("Kotlin");
        behaviorSubject.onNext("Javascript");

        behaviorSubject.subscribe(getSecondObserver());

        behaviorSubject.onNext("Html");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getThirdObserver());

    }

    void publishSubjectDemo1() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "Javascript", "HTML")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        PublishSubject<String> publishSubject= PublishSubject.create();
        observable.subscribe(publishSubject);

        publishSubject.subscribe(getFirstObserver());
        publishSubject.subscribe(getSecondObserver());
        publishSubject.subscribe(getThirdObserver());

    }

    void publishSubjectDemo2() {


        PublishSubject<String> publishSubject= PublishSubject.create();

        publishSubject.subscribe(getFirstObserver());

        publishSubject.onNext("Java");
        publishSubject.onNext("Kotlin");
        publishSubject.onNext("Javascript");

        publishSubject.subscribe(getSecondObserver());

        publishSubject.onNext("Html");
        publishSubject.onComplete();

        publishSubject.subscribe(getThirdObserver());

    }

    void replaySubjectDemo1() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "Javascript", "HTML")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        ReplaySubject<String> replaySubject= ReplaySubject.create();
        observable.subscribe(replaySubject);

        replaySubject.subscribe(getFirstObserver());
        replaySubject.subscribe(getSecondObserver());
        replaySubject.subscribe(getThirdObserver());

    }

    void replaySubjectDemo2() {


        ReplaySubject<String> replaySubject= ReplaySubject.create();

        replaySubject.subscribe(getFirstObserver());

        replaySubject.onNext("Java");
        replaySubject.onNext("Kotlin");
        replaySubject.onNext("Javascript");

        replaySubject.subscribe(getSecondObserver());

        replaySubject.onNext("Html");
        replaySubject.onComplete();

        replaySubject.subscribe(getThirdObserver());

    }


    private Observer<String> getFirstObserver() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {


                Log.i(TAG, " First Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {

                Log.i(TAG, " First Observer Received " + s);

            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " First Observer onError ");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " First Observer onComplete ");

            }
        };

        return observer;
    }

    private Observer<String> getSecondObserver() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {


                Log.i(TAG, " Second Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {

                Log.i(TAG, " Second Observer Received " + s);

            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " Second Observer onError ");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " Second Observer onComplete ");

            }
        };

        return observer;
    }

    private Observer<String> getThirdObserver() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {


                Log.i(TAG, " Third Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {

                Log.i(TAG, " Third Observer Received " + s);

            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " Third Observer onError ");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " Third Observer onComplete ");

            }
        };

        return observer;
    }

}

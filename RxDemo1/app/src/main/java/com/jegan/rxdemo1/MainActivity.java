package com.jegan.rxdemo1;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "RxDemo1";
    private String greeting = "Hello from RxJava";
    private Observable<String> myObservable;
    private DisposableObserver<String> myObserver;
    private DisposableObserver<String> myObserver2;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    //private Disposable disposable;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvGreeting);
        myObservable = Observable.just(greeting);


        //myObservable.observeOn(AndroidSchedulers.mainThread());
        myObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "on Next invoked");
                textView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "on Error invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "on Complete invoked");
            }
        };
        compositeDisposable.add(myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(myObserver));

        //compositeDisposable.add(myObserver);
        //myObservable.subscribe(myObserver);

        myObserver2 = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "on Next invoked");
                //textView.setText(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "on Error invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "on Complete invoked");
            }
        };

        //compositeDisposable.add(myObserver2);
        //myObservable.subscribe(myObserver2);

        compositeDisposable.add(myObservable
                .subscribeWith(myObserver2));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
        /*myObserver.dispose();
        myObserver2.dispose();*/
    }
}

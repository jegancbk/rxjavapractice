package com.jegan.rangedemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "myApp";
    private Integer[] nums = {1, 2, 3, 4, 5};
    private Observable<Integer> myObservable;
    private DisposableObserver<Integer> myObserver;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myObservable = Observable.range(1, 20);

        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
        );
    }

    private DisposableObserver getObserver(){

        myObserver = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer s) {


                Log.i(TAG, " onNext invoked "+s);
                Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " onError invoked");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " onComplete invoked");
            }
        };


        return myObserver;
    }
}

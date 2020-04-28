package com.androidtutz.jegan.tmdbclient.model;

import android.app.Application;

import com.androidtutz.jegan.tmdbclient.R;
import com.androidtutz.jegan.tmdbclient.service.MovieDataService;
import com.androidtutz.jegan.tmdbclient.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository {

    private Application application;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private ArrayList<Movie> movies;
    private Observable<MovieDBResponse> movieDBResponseObservable;

    public MovieRepository(Application application) {
        this.application = application;

        movies = new ArrayList<>();
        MovieDataService movieDataService = RetrofitInstance.getService();

        movieDBResponseObservable = movieDataService.getPopularMoviesWithRx(application.getString(R.string.api_key));

        compositeDisposable.add(movieDBResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<MovieDBResponse, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> apply(MovieDBResponse movieDBResponse) throws Exception {
                        return Observable.fromArray(movieDBResponse.getMovies().toArray(new Movie[0]));
                    }
                })
                .filter(new Predicate<Movie>() {
                    @Override
                    public boolean test(Movie movie) throws Exception {
                        return movie.getVoteAverage() > 7;
                    }
                })
                .subscribeWith(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie movie) {
                        movies.add(movie);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        moviesLiveData.postValue(movies);
                    }
                }));
    }

    public MutableLiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void clear() {
        compositeDisposable.clear();
    }
}

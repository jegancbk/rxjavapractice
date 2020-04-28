package com.androidtutz.jegan.tmdbclient.ViewModel;

import android.app.Application;

import com.androidtutz.jegan.tmdbclient.model.Movie;
import com.androidtutz.jegan.tmdbclient.model.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMoviesLiveData();
    }

    public void clear() {
        movieRepository.clear();
    }
}

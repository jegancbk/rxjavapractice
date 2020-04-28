package com.androidtutz.jegan.tmdbclient.view;

import android.content.res.Configuration;
import android.os.Bundle;

import com.androidtutz.jegan.tmdbclient.R;
import com.androidtutz.jegan.tmdbclient.ViewModel.MainActivityViewModel;
import com.androidtutz.jegan.tmdbclient.adapter.MovieAdapter;
import com.androidtutz.jegan.tmdbclient.model.Movie;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("TMDB Popular Movies Today");

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        getPopularMoviesRx();


        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getPopularMoviesRx();

            }
        });

    }

    public void getPopularMoviesRx() {

        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesList) {
                movies = (ArrayList<Movie>) moviesList;
                showOnRecyclerView();

            }
        });


    }

    private void showOnRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.rvMovies);
        movieAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mainActivityViewModel.clear();
        //compositeDisposable.clear();
        /*if (call != null) {
            if (call.isExecuted()) {
                call.cancel();
            }
        }*/
    }
}

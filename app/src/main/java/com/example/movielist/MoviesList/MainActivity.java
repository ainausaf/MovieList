package com.example.movielist.MoviesList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.ProgressBar;

import com.example.movielist.BaseActivity;
import com.example.movielist.MovieDetails.MovieDetailsActivity;
import com.example.movielist.R;
import com.example.movielist.model.MoviesData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity  implements  MainActivityContract.View{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    MainActivityContract.Presenter mainActivityPresenter;

    private MainActivityComponent mainActivityComponent;
    private MoviesRecyclerAdapter recyclerAdapter;
    private List<MoviesData> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDaggerAppComponent();
        ActionBar actionbar = this.getSupportActionBar();
        if(actionbar!=null) {
            actionbar.setDisplayShowHomeEnabled(true);
            actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1B2D1B")));
            actionbar.setIcon(R.drawable.ic_launcher_foreground);
        }
        movieList = new ArrayList<>();
        initViews();
        mainActivityPresenter.onCreate();
    }

    private void initViews() {
        recyclerAdapter = new MoviesRecyclerAdapter(this, movieList, movie -> startDetailsActivity(movie));
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    // response to the on click event on the recycler view
    private void startDetailsActivity(MoviesData movie) {
      Intent i = new Intent(this, MovieDetailsActivity.class);
      i.putExtra("movieDataforId", (Parcelable) movie);
      startActivity(i);
    }

    public void getMoviesList(List<MoviesData> moviesListData){
        movieList = moviesListData;
        recyclerAdapter.addAll(movieList);
    }

    @Override
    public void showProgressBar(boolean isDisplayProgressBar) {
        if (isDisplayProgressBar)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage() {
        System.out.println("APP Crashed");
    }

    private void initDaggerAppComponent() {
        super.initDaggerComponent();
        if (mainActivityComponent == null) {
            mainActivityComponent = DaggerMainActivityComponent.builder()
                    .appComponent(appComponent)
                    .mainActivityModule(new MainActivityModule(this))
                    .build();
            mainActivityComponent.inject(this);
        }
    }

}

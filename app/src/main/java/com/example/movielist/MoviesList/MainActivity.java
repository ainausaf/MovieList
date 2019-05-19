package com.example.movielist.MoviesList;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.ProgressBar;

import com.example.movielist.BaseActivity;
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

       getSupportActionBar().setDisplayShowHomeEnabled(true);
       getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);
        movieList = new ArrayList<>();
        initViews();
        mainActivityPresenter.onCreate();
    }

    private void initViews() {
        recyclerAdapter = new MoviesRecyclerAdapter(this, movieList);
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    // response to the on click event on the recycler view
    private void startDetailsActivity() {
       // mainActivityPresenter.getTutorialsData(video.getId());
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

    //passing the list from the second API call to TutorialsFeedDetailsActivity
 /*   @Override
    public void navigateToMovieDetails() {
        Intent i = new Intent(this, TutorialFeedDetailsActivity.class);
        i.putExtra("tutorialDetails", (Serializable) tutorialDetails);
        startActivity(i);
    }*/

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

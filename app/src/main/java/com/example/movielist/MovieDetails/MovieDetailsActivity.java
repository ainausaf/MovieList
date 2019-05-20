package com.example.movielist.MovieDetails;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movielist.BaseActivity;
import com.example.movielist.R;
import com.example.movielist.data.Repository;
import com.example.movielist.model.MovieDetail;
import com.example.movielist.model.MoviesData;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsContract.View {

    @BindView(R.id.movie_index)
    TextView movieIndex;

    @BindView(R.id.iv_movie_detail_view)
    ImageView movieDetailImage;

    @BindView(R.id.movie_detail_title)
    TextView movieTitle;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    MovieDetailsContract.Presenter movieDetailsPresenter;

    @Inject
    Repository repository;

    private MovieDetailsComponent movieDetailsComponent;
    private MovieDetail movieDetail;
    private MoviesData movieData;


    @Override
    protected void onCreate(Bundle onSaveInstance){
        super.onCreate(onSaveInstance);
        setContentView(R.layout.movie_details);
        ButterKnife.bind(this);
        initMovieDetailDaggerComponent();
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1B2D1B")));
        movieData = getIntent().getParcelableExtra("movieDataforId");
        movieDetailsPresenter.onCreate(movieData);
    }

    @Override
    public void initViews(MovieDetail movieDetail) {
        this.movieDetail = movieDetail;
        showProgressBar(false);
        movieIndex.setText("Movie Number from the list is "+movieDetail.getIndex());
        String movieImageUrl = movieDetail.getImage();
        Glide.with(this).load( movieImageUrl ).placeholder(
                R.drawable.layout_placeholder).error(
                R.drawable.layout_placeholder).into(movieDetailImage);
        movieTitle.setText(movieDetail.getTitle());
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

    private void initMovieDetailDaggerComponent() {
        super.initDaggerComponent();
        if (movieDetailsComponent == null) {
            movieDetailsComponent = DaggerMovieDetailsComponent.builder()
                    .appComponent(appComponent)
                    .movieDetailsModule (new MovieDetailsModule (this))
                    .build();
            movieDetailsComponent.inject(this);
        }
    }
}

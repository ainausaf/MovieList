package com.example.movielist.uiTest;


import com.example.movielist.MoviesList.MainActivityContract;
import com.example.movielist.MoviesList.MainActivityPresenter;
import com.example.movielist.data.Repository;
import com.example.movielist.model.MoviesData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Presenter
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainActivityContract.View mockView;

    @Mock
    private Repository mockDataRepository;

    @Mock
    private MoviesData moviesData;
    @Mock
    private List<MoviesData> moviesDataList;

    private MainActivityContract.Presenter presenter;

    @Before
    public void setup() {
        presenter = new MainActivityPresenter(mockView, mockDataRepository);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void getFeedData_testCallBack(){
        moviesDataList = new ArrayList<>();

        when(mockDataRepository.getMoviesData()).thenReturn(Observable.just(moviesDataList));

        presenter.getMoviesData();

        verify(mockDataRepository).getMoviesData();

        verify(mockView, times(1)).showProgressBar(false);
    }
}

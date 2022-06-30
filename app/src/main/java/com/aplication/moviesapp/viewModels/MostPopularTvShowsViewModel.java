package com.aplication.moviesapp.viewModels;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aplication.moviesapp.models.TvShow;
import com.aplication.moviesapp.models.TvShowDetails;
import com.aplication.moviesapp.repositories.MostPopularTvShowsRepo;
import com.aplication.moviesapp.repositories.TvShowDetailRepo;

import java.util.ArrayList;

public class MostPopularTvShowsViewModel extends ViewModel {


    private  Context context;

    // fetching most popular TvShows
    private  static MostPopularTvShowsRepo mostPopularTvShowsRepo;

    public  void loadMostPopularTvShowsRepository(Context context ) {
       this.context = context;
       this.mostPopularTvShowsRepo = MostPopularTvShowsRepo.getInstance(context);
    }
    public MutableLiveData<ArrayList<TvShow>> getMostPopularTvShowsData(int page) {
        MutableLiveData<ArrayList<TvShow>> data =  this.mostPopularTvShowsRepo.getMostPopularTvShowRepo(page);
        return data;
    }





}

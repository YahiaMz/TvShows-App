package com.aplication.moviesapp.viewModels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aplication.moviesapp.models.TvShow;
import com.aplication.moviesapp.repositories.SearchRepository;

import java.util.ArrayList;

public class search_view_model extends ViewModel {

    private SearchRepository searchRepository;
    private Context mContext;


    public void initializeSearchRepository (Context context) {
        mContext = context;
        this.searchRepository = SearchRepository.getInstance(context);
    }

    public MutableLiveData< ArrayList<TvShow> > search (String movieName ) {
         MutableLiveData<ArrayList<TvShow>> searchResult = this.searchRepository.getSearchResult(movieName);
         return searchResult;
    }

}

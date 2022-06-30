package com.aplication.moviesapp.viewModels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aplication.moviesapp.models.TvShowDetails;
import com.aplication.moviesapp.repositories.TvShowDetailRepo;

public class TvShowDetailsViewModel extends ViewModel {

      private MutableLiveData<TvShowDetails> tvShowDetailsMutableLiveData;
      private TvShowDetailRepo tvShowDetailRepo;

      public void loadTvShowRepository (Context context) {
          this.tvShowDetailRepo = TvShowDetailRepo.getInstance(context);
      }

      public MutableLiveData<TvShowDetails> getTvShowDetailsMutableLiveData (int tvShowId ) {
          this.tvShowDetailsMutableLiveData = this.tvShowDetailRepo.fetchTvShowDetails(tvShowId);
          return this.tvShowDetailsMutableLiveData;
          }
}

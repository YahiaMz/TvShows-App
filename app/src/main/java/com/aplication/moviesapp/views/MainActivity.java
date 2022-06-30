package com.aplication.moviesapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aplication.moviesapp.adapters.PopularTvShowsAdapter;
import com.aplication.moviesapp.R;
import com.aplication.moviesapp.databinding.ActivityMainBinding;
import com.aplication.moviesapp.viewModels.MostPopularTvShowsViewModel;

public class MainActivity extends AppCompatActivity implements PopularTvShowsAdapter.IonClick {
    ActivityMainBinding activityMainBinding;
    MostPopularTvShowsViewModel viewModel;
    private PopularTvShowsAdapter tvShowsAdapter;

  private  int currentPage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        viewModel = new ViewModelProvider(this
        ).get(MostPopularTvShowsViewModel.class);
        viewModel.loadMostPopularTvShowsRepository(this);



       getMostPopularTvShows();
       activityMainBinding.setMostPopularTvShowsAdapter(this.tvShowsAdapter);
        this.activityMainBinding.moviesRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if ( !activityMainBinding.moviesRv.canScrollVertically(1) && !activityMainBinding.getIsLoadingMoreMovies() ) {
                    currentPage ++;
                    getMostPopularTvShows();
                }
            }
        });

        this.OnSearchBtnClicked();


    }

    private  void getMostPopularTvShows( ) {

        this.handleProgressBars();
        viewModel.getMostPopularTvShowsData(currentPage).observe(this , tvShows -> {

            if (this.tvShowsAdapter == null)   {
                this.tvShowsAdapter = new PopularTvShowsAdapter(tvShows  , this);
                activityMainBinding.setMostPopularTvShowsAdapter(this.tvShowsAdapter);
            }
            else {
                int itemCountBeforeAdding = tvShowsAdapter.getItemCount();
                this.tvShowsAdapter.getTvShowsList().addAll(tvShows);
                int itemCountAfterAdding = this.tvShowsAdapter.getItemCount();
                this.tvShowsAdapter.notifyItemRangeInserted(itemCountBeforeAdding , itemCountAfterAdding );
            }
        });

        this.activityMainBinding.setIsLoading(false);
        this.activityMainBinding.setIsLoadingMoreMovies(false);
    }
    private void handleProgressBars(  ) {
        if ( this.currentPage == 1) {
            this.activityMainBinding.setIsLoading(true);
    } else {
            this.activityMainBinding.setIsLoadingMoreMovies(true);
       }
    }
    @Override
    public void onClick(int tvShowId) {
        Intent intent = new Intent(this , TvShowDetails.class);
        intent.putExtra("id" , tvShowId);
        startActivity(intent);
    }
    private  void OnSearchBtnClicked( ) {
        this.activityMainBinding.SearchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSearchScreen();
            }
        });


    }

    private  void goToSearchScreen( ) {
        Intent intent = new Intent(this , search_activity.class);
        startActivity(intent);
    }


}
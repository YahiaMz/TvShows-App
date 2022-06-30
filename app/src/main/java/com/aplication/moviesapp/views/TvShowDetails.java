package com.aplication.moviesapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aplication.moviesapp.R;
import com.aplication.moviesapp.adapters.EpisodeAdapter;
import com.aplication.moviesapp.adapters.ImageSliderAdapter;
import com.aplication.moviesapp.databinding.ActivityTvShowDetailsBinding;
import com.aplication.moviesapp.models.Episode;
import com.aplication.moviesapp.viewModels.TvShowDetailsViewModel;
import com.squareup.picasso.Picasso;

import java.net.HttpCookie;
import java.util.ArrayList;

public class TvShowDetails extends AppCompatActivity {

    TvShowDetailsViewModel viewModel ;
    ActivityTvShowDetailsBinding activityTvShowDetailsBinding;
    int tvShowId;
    boolean isLoading = true;
    ImageSliderAdapter imageSliderAdapter;
    int currentImagePos =1 ;
    EpisodeAdapter episodeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);
      this.activityTvShowDetailsBinding = DataBindingUtil.setContentView(this , R.layout.activity_tv_show_details);

        Intent tvShowDetailsIntent = this.getIntent();
        // getting movie id for showing the details of this film
        Bundle extraData = tvShowDetailsIntent.getExtras();
        this.tvShowId = extraData.getInt("id");
        this.activityTvShowDetailsBinding.setReadMoreDescription(true);

        // initialization of view Model
        this.viewModel = new ViewModelProvider(this ).get(TvShowDetailsViewModel.class);
        this.viewModel.loadTvShowRepository(this);
        // end with initialization
        this.getTvShowDetails();
        this.read_More_Less();
        this.changeLessMoreOnDescriptionClicked();
        this.onBackBtnCLicked();

    }


    private void getTvShowDetails ( ) {
        viewModel.getTvShowDetailsMutableLiveData(this.tvShowId).observe(this, tvShowD -> {

            this.activityTvShowDetailsBinding.setTvShowDetailsData(tvShowD);
            this.activityTvShowDetailsBinding.tvDescription.setText(Html.fromHtml(tvShowD.getDescription()));
            Picasso.get().load(tvShowD.getImage()).into(this.activityTvShowDetailsBinding.shapeableImageView);
            this.activityTvShowDetailsBinding.setIsLoadingData(false);
            this.loadImagesIntoViewPager(tvShowD.getPictures());
            this.loadEpisodes(tvShowD.getEpisodes());


        });
    }

    public void loadEpisodes (ArrayList<Episode> episodes) {
        if ( this.episodeAdapter == null) {
          this.episodeAdapter = new EpisodeAdapter(episodes);
        }
        this.activityTvShowDetailsBinding.setEpisodesAdapter(this.episodeAdapter);
    }

    public void loadImagesIntoViewPager(ArrayList<String> images) {
        if ( this.imageSliderAdapter == null ) {
         this.imageSliderAdapter = new ImageSliderAdapter(images);
         loadIndicators(images.size());
         changeActiveIndicator(images.size());
         this.activityTvShowDetailsBinding.setViewPagerAdapter(this.imageSliderAdapter);
        }
        else {
            this.imageSliderAdapter.getImageUrls().clear();
            this.imageSliderAdapter.getImageUrls().addAll(images);
            this.imageSliderAdapter.notifyDataSetChanged();
        }
    }




    public void changeActiveIndicator ( int indicatorCount ) {
      this.activityTvShowDetailsBinding.vp2TvShowImages.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
              super.onPageScrolled(position, positionOffset, positionOffsetPixels);

              for ( int x =0 ;x < indicatorCount ; x++) {
                  activityTvShowDetailsBinding.llPoionts.getChildAt(x).setBackgroundResource(R.drawable.non_active_indicator);
              }
              activityTvShowDetailsBinding.llPoionts.getChildAt(position).setBackgroundResource(R.drawable.active_indicator);

          }
      });
    }
    public  void loadIndicators ( int indicatorCount) {


        ImageView indicator;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20 , 20);
        layoutParams.setMargins(4 ,0 ,4 , 0 );

        for ( int x = 0 ;  x< indicatorCount ; x ++ ) {

            indicator = new ImageView(this.getBaseContext());
            indicator.setLayoutParams(layoutParams);
            indicator.setBackgroundResource(R.drawable.non_active_indicator);
            this.activityTvShowDetailsBinding.llPoionts.addView(indicator);
        }


    }
    public void read_More_Less ( ) {
        this.activityTvShowDetailsBinding.btnReadMoreLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               toggleLessMore();
            }
        });
    }
    public  void changeLessMoreOnDescriptionClicked( ) {
        this.activityTvShowDetailsBinding.tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLessMore();
            }
        });
    }
    private  void toggleLessMore ( ) {
        if ( activityTvShowDetailsBinding.getReadMoreDescription() != null)
            activityTvShowDetailsBinding.setReadMoreDescription(! activityTvShowDetailsBinding.getReadMoreDescription());
    }
    private  void onBackBtnCLicked( ) {
        this.activityTvShowDetailsBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}
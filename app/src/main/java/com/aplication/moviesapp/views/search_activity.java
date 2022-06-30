package com.aplication.moviesapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.aplication.moviesapp.R;
import com.aplication.moviesapp.adapters.PopularTvShowsAdapter;
import com.aplication.moviesapp.databinding.ActivitySearchBinding;
import com.aplication.moviesapp.viewModels.search_view_model;

public class search_activity extends AppCompatActivity implements PopularTvShowsAdapter.IonClick{
    ActivitySearchBinding activitySearchBinding;
    private search_view_model view_model ;
    private PopularTvShowsAdapter searchTvShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
         activitySearchBinding = DataBindingUtil.setContentView(this , R.layout.activity_search);
         view_model = new ViewModelProvider(this).get(search_view_model.class);
         view_model.initializeSearchRepository(this);

         this.activitySearchBinding.editTextTextPersonName.setOnKeyListener(new View.OnKeyListener() {
             @Override
             public boolean onKey(View view, int i, KeyEvent keyEvent) {

                 if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                     view_model.search(activitySearchBinding.editTextTextPersonName.getText().toString()).observe(search_activity.this , searchResult -> {

                         if ( searchResult == null ) {
                             activitySearchBinding.setDataFound(false);
                         } else {
                             activitySearchBinding.setDataFound(true);
                             if ( searchTvShowAdapter == null) {
                                 searchTvShowAdapter = new PopularTvShowsAdapter(searchResult , search_activity.this);
                                 activitySearchBinding.setAdapter(searchTvShowAdapter);
                              } else {
                                  searchTvShowAdapter.getTvShowsList().clear();
                                  searchTvShowAdapter.getTvShowsList().addAll(searchResult);
                                  searchTvShowAdapter.notifyDataSetChanged();
                             }

                         }

                     });
                 }
                 return false;
             }
         });


this.activitySearchBinding.btnBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
});


    }

    @Override
    public void onClick(int tvShowId) {
        Intent intent = new Intent(this , TvShowDetails.class);
        intent.putExtra("id" , tvShowId);
        Toast.makeText(this, tvShowId + "", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
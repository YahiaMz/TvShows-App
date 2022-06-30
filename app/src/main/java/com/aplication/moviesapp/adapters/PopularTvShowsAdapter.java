package com.aplication.moviesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aplication.moviesapp.databinding.ItemContainerBinding;
import com.aplication.moviesapp.models.TvShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PopularTvShowsAdapter extends RecyclerView.Adapter<PopularTvShowsAdapter.TvShowViewHolder> {


    public  interface IonClick {
        public void onClick (int tvShowId);
    }

    IonClick ionClick;

    public ArrayList<TvShow> getTvShowsList() {
        return tvShowsList;
    }
    public void setTvShowsList(ArrayList<TvShow> tvShowsList) {
        this.tvShowsList = tvShowsList;
    }

    private ArrayList<TvShow> tvShowsList;
    private LayoutInflater mLayoutInflater;


    public  PopularTvShowsAdapter() { }
    public  PopularTvShowsAdapter( ArrayList<TvShow> tvShows ) {
        this.tvShowsList = tvShows;
    }

    public PopularTvShowsAdapter(  ArrayList<TvShow> tvShowsList , IonClick ionClick)  {
        this.tvShowsList = tvShowsList;
        this.ionClick =ionClick;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mLayoutInflater == null)
            mLayoutInflater = LayoutInflater.from(parent.getContext());

        ItemContainerBinding tvShowContainer = ItemContainerBinding.inflate(mLayoutInflater , parent , false);
        return new TvShowViewHolder(tvShowContainer);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
            holder.bind(tvShowsList.get(position));
    }

    @Override
    public int getItemCount() {
        return  this.tvShowsList.size();
    }
    public  class  TvShowViewHolder extends RecyclerView.ViewHolder {
        private ItemContainerBinding tvShowContainer;

        public TvShowViewHolder(@NonNull ItemContainerBinding tvShowContainer) {
            super(tvShowContainer.getRoot());
            this.tvShowContainer = tvShowContainer;
        }

        public  void bind(TvShow tvShow) {
            this.tvShowContainer.setTvShow(tvShow);
            Picasso.get().load(tvShow.getImage()).into(this.tvShowContainer.movieImage);

            this.tvShowContainer.tvShowContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ionClick.onClick(tvShow.getId());
                }
            });


        }

    }
}

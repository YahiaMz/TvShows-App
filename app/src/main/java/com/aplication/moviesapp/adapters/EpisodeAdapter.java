package com.aplication.moviesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aplication.moviesapp.R;
import com.aplication.moviesapp.databinding.EpisodeItemContainerBinding;
import com.aplication.moviesapp.models.Episode;

import java.util.ArrayList;

    public class EpisodeAdapter extends  RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>{

    private ArrayList<Episode> episodes;
    private LayoutInflater layoutInflater;

        public ArrayList<Episode> getEpisodes() {
            return episodes;
        }

        public void setEpisodes(ArrayList<Episode> episodes) {
            this.episodes = episodes;
        }

        public EpisodeAdapter(ArrayList<Episode> episodes) {
            this.episodes = episodes;
        }

        @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if( layoutInflater == null ) {
          layoutInflater = LayoutInflater.from(parent.getContext());
         }
        EpisodeItemContainerBinding episodeItemContainerBinding = DataBindingUtil.inflate(this.layoutInflater , R.layout.episode_item_container , parent , false);
        return new EpisodeViewHolder(episodeItemContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode episode = this.episodes.get(position);
        holder.bind(episode);

    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }





    public class EpisodeViewHolder extends RecyclerView.ViewHolder {

        private  EpisodeItemContainerBinding episodeItemContainerBinding ;
        public EpisodeViewHolder(@NonNull EpisodeItemContainerBinding episodeItemContainerBinding) {
            super(episodeItemContainerBinding.getRoot());
            this.episodeItemContainerBinding = episodeItemContainerBinding;
        }

        private void bind ( Episode episode) {
            this.episodeItemContainerBinding.setEpisodeData(episode);
        }
    }

}

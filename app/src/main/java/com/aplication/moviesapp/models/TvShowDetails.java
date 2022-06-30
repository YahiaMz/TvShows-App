package com.aplication.moviesapp.models;

import java.util.ArrayList;

public class TvShowDetails extends  TvShow{
    public TvShowDetails(int id, String name, String start_date, String country, String network, String state, String image) {
        super(id, name, start_date, country, network, state, image);
    }
    private  String description ;
    private int runtime;
    private String youtube_link;
    private String rating;
    private int rating_count;
    private ArrayList<String> genres ;
    private ArrayList<String> pictures ;
    private ArrayList<Episode> episodes;


    // constructors

    public TvShowDetails(int id, String name, String start_date, String country, String network, String state, String image,
                         String description, int runtime, String youtube_link, String rating, int rating_count, ArrayList<String> genres, ArrayList<String> pictures, ArrayList<Episode> episodes) {
        super(id, name, start_date, country, network, state, image);
        this.description = description;
        this.runtime = runtime;
        this.youtube_link = youtube_link;
        this.rating = rating;
        this.rating_count = rating_count;
        this.genres = genres;
        this.pictures = pictures;
        this.episodes = episodes;
    }


    // getter && setters


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getYoutube_link() {
        return youtube_link;
    }

    public void setYoutube_link(String youtube_link) {
        this.youtube_link = youtube_link;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }
}

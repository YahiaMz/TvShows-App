package com.aplication.moviesapp.models;

public class Episode {
    private int season;
    private int episode ;
    private  String name;
    String air_date ;

    public Episode(int season, int episode, String name, String air_date) {
        this.season = season;
        this.episode = episode;
        this.name = name;
        this.air_date = air_date;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }
}

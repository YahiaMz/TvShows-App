package com.aplication.moviesapp.models;

public class TvShow {

    private  int id;
    private  String name;
    private  String start_date;
    private  String country;
    private   String network;
    private  String state;
    private  String image;

    public TvShow(int id, String name, String start_date, String country, String network, String state, String image) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.country = country;
        this.network = network;
        this.state = state;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getCountry() {
        return country;
    }

    public String getNetwork() {
        return network;
    }

    public String getState() {
        return state;
    }

    public String getImage() {
        return image;
    }
}

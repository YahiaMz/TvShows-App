package com.aplication.moviesapp.responses;

import com.aplication.moviesapp.models.TvShow;

import java.util.ArrayList;

public class mostPopularTvShowsResponse {

    private int total;
    private int page ;
    private int pages ;

    private ArrayList<TvShow> tvShows;

    public mostPopularTvShowsResponse(int total, int page, int pages ) {
        this.total = total;
        this.page = page;
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<TvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
    }
}

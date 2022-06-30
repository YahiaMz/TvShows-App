package com.aplication.moviesapp.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplication.moviesapp.helper.TvShowUrls;
import com.aplication.moviesapp.models.TvShow;
import com.aplication.moviesapp.responses.mostPopularTvShowsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchRepository {

    private static SearchRepository search_repository;
    private Context context;

    public SearchRepository(Context context) {
        this.context = context;
    }

    public  static  SearchRepository getInstance(Context context) {
        if ( search_repository == null ) search_repository = new SearchRepository(context);
        return  search_repository;
    }

    public MutableLiveData<ArrayList<TvShow>> getSearchResult ( String movieName ) {

        MutableLiveData<ArrayList<TvShow>> tvShows = new MutableLiveData<>();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, TvShowUrls.SearchTvShow+movieName, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject mResponse = new JSONObject(response);
                    mostPopularTvShowsResponse mostPopularTvShowsResponse = new mostPopularTvShowsResponse(mResponse.getInt("total") ,
                            mResponse.getInt("page") ,
                            mResponse.getInt("pages")
                    );

                    // filling the array list
                    ArrayList<TvShow> tv_ShowsArrayList = new ArrayList<TvShow>();
                    JSONArray tv_shows_json = mResponse.getJSONArray("tv_shows");

                    for ( int x = 0 ; x <tv_shows_json.length(); x ++  ) {
                        JSONObject cTvShowJson = (JSONObject) tv_shows_json.get(x);
                        TvShow cTvShow = new TvShow(cTvShowJson.getInt("id") ,
                                cTvShowJson.getString("name") ,
                                cTvShowJson.getString("start_date") ,
                                cTvShowJson.getString("country") ,
                                cTvShowJson.getString("network") ,
                                cTvShowJson.getString("status") ,
                                cTvShowJson.getString("image_thumbnail_path")
                        );
                        tv_ShowsArrayList.add(cTvShow);
                    }
                    // end filling

                    tvShows.setValue(tv_ShowsArrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "catch : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
        return tvShows;
    }
}

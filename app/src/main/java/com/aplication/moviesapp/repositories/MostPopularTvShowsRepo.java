package com.aplication.moviesapp.repositories;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplication.moviesapp.helper.TvShowUrls;
import com.aplication.moviesapp.models.TvShow;
import com.aplication.moviesapp.responses.mostPopularTvShowsResponse;
import com.aplication.moviesapp.views.no_internet_connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;

public class MostPopularTvShowsRepo {

    private Context mContext;
    private  MostPopularTvShowsRepo( Context context ) {
        mContext =context;

    }
    private  static MostPopularTvShowsRepo mostPopularTvShowsRepo;
    public  static MostPopularTvShowsRepo getInstance ( Context context  ){
        if ( mostPopularTvShowsRepo == null )
            mostPopularTvShowsRepo = new MostPopularTvShowsRepo(context);
        return  mostPopularTvShowsRepo;
    }


    public   MutableLiveData<ArrayList<TvShow>> getMostPopularTvShowRepo ( int page) {
           final   MutableLiveData<ArrayList<TvShow>> tvShows = new MutableLiveData<ArrayList<TvShow>>();

         RequestQueue requestQueue = Volley.newRequestQueue(mContext);
    //    Toast.makeText(mContext, "getting data from" + TvShowUrls.mostPopularTvShows+page, Toast.LENGTH_SHORT).show();
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, TvShowUrls.mostPopularTvShows + page,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mContext, "catch : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                 } ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                            Intent toErrorActivity = new Intent(mContext, no_internet_connection.class);
                            mContext.startActivity(toErrorActivity);

                    }
        }
        );

        requestQueue.add(mStringRequest);
        
        return tvShows;
    }




}

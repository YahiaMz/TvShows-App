package com.aplication.moviesapp.repositories;

import android.content.Context;
import android.content.Intent;
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
import com.aplication.moviesapp.models.Episode;
import com.aplication.moviesapp.models.TvShowDetails;
import com.aplication.moviesapp.views.no_internet_connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TvShowDetailRepo {


    private  Context mContext;
    private static TvShowDetailRepo tvShowDetailRepo;

    // it's a class that support singleton design pattern
    private  TvShowDetailRepo( Context context) {
        mContext = context;
    }

    public  static  TvShowDetailRepo getInstance( Context context) {
        if ( tvShowDetailRepo == null) {
            tvShowDetailRepo = new TvShowDetailRepo(context);
         }
        return  tvShowDetailRepo;
   }

   // start fetching data :
    private RequestQueue mRequestQueue ;
    private StringRequest stringRequest;
    private MutableLiveData<TvShowDetails> mutableTvShowDetails = new MutableLiveData<TvShowDetails>();



    public MutableLiveData<TvShowDetails> fetchTvShowDetails ( int tvShowId ) {

        this.mRequestQueue = Volley.newRequestQueue(this.mContext);
        stringRequest = new StringRequest(Request.Method.GET, TvShowUrls.TvShowDetailsUrl + tvShowId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jResponse = new JSONObject(response);


                    JSONObject tvShowJSON =  jResponse.getJSONObject("tvShow");

                    // general information {
                    int id = tvShowJSON.getInt("id");
                    String name = tvShowJSON.getString("name");
                    String description = tvShowJSON.getString("description");
                    String start_date  = tvShowJSON.getString("start_date");
                    String country = tvShowJSON.getString("country");
                    String status = tvShowJSON.getString("status");
                    int runtime = tvShowJSON.getInt("runtime");
                    String network = tvShowJSON.getString("network");
                    String youtube_link = tvShowJSON.getString("youtube_link");
                    String image_path = tvShowJSON.getString("image_path");
                    String rating = tvShowJSON.getString("rating");
                    int rating_count = tvShowJSON.getInt("rating_count");
                    // }

                    // filling genres

                    JSONArray jaGenres = tvShowJSON.getJSONArray("genres");
                    ArrayList<String> stringArrayListOfGenres = new ArrayList<>();
                    for (int x = 0; x < jaGenres.length(); x++) {
                        stringArrayListOfGenres.add(jaGenres.get(x).toString());
                    }
                    // end  filling genres

                    // filling  images
                    ArrayList<String> picturesArrList = new ArrayList<String>();
                    JSONArray mJSON_pictures = tvShowJSON.getJSONArray("pictures");
                    for (int x = 0; x < mJSON_pictures.length(); x++) {
                        picturesArrList.add(mJSON_pictures.get(x).toString());
                    }
                    // end end with filling images


                    // getting episodes
                    ArrayList<Episode> episodesArrList = new ArrayList<>();
                    JSONArray episodesJSON_Arr = tvShowJSON.getJSONArray("episodes");
                    for (int x = 0 ; x < episodesJSON_Arr.length() ; x++ ) {
                        JSONObject currentEpisode = (JSONObject) episodesJSON_Arr.get(x);

                        int season = currentEpisode.getInt("season");
                        int episode = currentEpisode.getInt("episode");
                        String Ep_name = currentEpisode.getString("name");
                        String air_date = currentEpisode.getString("air_date");

                      episodesArrList.add(new Episode(season , episode , Ep_name , air_date));
                    }
                    // getting episodes

                   TvShowDetails tvShowDetails = new TvShowDetails(
                            id ,
                            name ,
                            start_date,
                            country ,
                            network,
                            status ,
                            image_path ,
                            description ,
                            runtime ,
                            youtube_link ,
                            rating ,
                            rating_count ,
                            stringArrayListOfGenres ,
                            picturesArrList ,
                            episodesArrList
                    );

                    mutableTvShowDetails.setValue(tvShowDetails);

                }catch (JSONException e) {
                    Toast.makeText(mContext, "in catch : { " + e.getMessage() + "}", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error ) {
                if (error instanceof NetworkError) {
                    Intent toErrorActivity = new Intent(mContext, no_internet_connection.class);
                    mContext.startActivity(toErrorActivity);

                }
            }
        });

        this.mRequestQueue.add(stringRequest);
        return mutableTvShowDetails;
    }






}

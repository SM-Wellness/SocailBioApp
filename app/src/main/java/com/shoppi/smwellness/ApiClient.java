package com.shoppi.smwellness;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiClient {
    private static final String BASE_URL = "http://nn-env.eba-yt6y2is3.ap-northeast-2.elasticbeanstalk.com/";


    public static Retrofit_interface getApiService(){return getInstance().create(Retrofit_interface.class);}

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}


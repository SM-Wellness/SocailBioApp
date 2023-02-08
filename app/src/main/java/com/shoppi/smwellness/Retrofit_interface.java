package com.shoppi.smwellness;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Retrofit_interface {

    @GET("dummy/{dummy}")
    Call<data_model> test_api_get(
            @Path("dummy") String userid);
}
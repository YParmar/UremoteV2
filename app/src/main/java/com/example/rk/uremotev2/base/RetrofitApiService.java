package com.example.rk.uremotev2.base;

import com.example.rk.uremotev2.model.FeedSwitchRequest;
import com.example.rk.uremotev2.model.FeedSwitchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Niel on 24/07/17.
 */

public interface RetrofitApiService {

    @POST("feeds/{switch_type}/data")
    Call<FeedSwitchResponse> doFeedSwitchOnOFApiCall(@Path("switch_type") String switchType, @Body FeedSwitchRequest request);
}
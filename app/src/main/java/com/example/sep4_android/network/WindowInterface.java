package com.example.sep4_android.network;

import com.example.sep4_android.model.Window;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WindowInterface {
    @POST("/newWindow")
    Call<String> sendWindowStatus(@Body Window window);
}

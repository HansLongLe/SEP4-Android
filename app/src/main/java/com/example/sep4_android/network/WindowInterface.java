package com.example.sep4_android.network;

import com.example.sep4_android.model.Window;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WindowInterface {
    @POST("/newWindow")
    Call<Window> sendWindowStatus(@Body JsonObject jsonObject);
    @GET("/windows")
    Call<ArrayList<Window>> getWindowStatus();
}

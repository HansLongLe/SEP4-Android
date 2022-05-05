package com.example.sep4_android.network;

import com.example.sep4_android.model.CO2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataRetrieveInterface {
    @GET("/co2Sensors")
    Call<ArrayList<CO2>> getAllCO2();
}

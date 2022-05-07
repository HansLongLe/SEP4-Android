package com.example.sep4_android.network;

import com.example.sep4_android.model.CO2;
import com.example.sep4_android.model.Humidity;
import com.example.sep4_android.model.Motion;
import com.example.sep4_android.model.Temperature;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataRetrieveInterface {
    @GET("/co2Sensors")
    Call<ArrayList<CO2>> getAllCO2();
    @GET("/humiditySensors")
    Call<ArrayList<Humidity>> getAllHumidity();
    @GET("/temperatures")
    Call<ArrayList<Temperature>> getAllTemperature();
    @GET("/motionSensors")
    Call<ArrayList<Motion>> getAllMotion();
}

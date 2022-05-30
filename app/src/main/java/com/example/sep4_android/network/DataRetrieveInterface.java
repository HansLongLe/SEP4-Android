package com.example.sep4_android.network;

import com.example.sep4_android.model.CO2;
import com.example.sep4_android.model.Humidity;
import com.example.sep4_android.model.Sensor;
import com.example.sep4_android.model.Temperature;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataRetrieveInterface {
    @GET("/temperatures")
    Call<ArrayList<Temperature>> getTemperatures();
    @GET("/humiditySensors")
    Call<ArrayList<Humidity>> getHumidities();
    @GET("/co2Sensors")
    Call<ArrayList<CO2>> getCO2s();
}

package com.example.sep4_android.network;

import com.example.sep4_android.model.Sensor;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataRetrieveInterface {
    @GET("/sensors")
    Call<ArrayList<Sensor>> getSensors();
}

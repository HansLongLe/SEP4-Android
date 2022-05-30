package com.example.sep4_android.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.CO2;
import com.example.sep4_android.model.Sensor;
import com.example.sep4_android.network.DataRetrieveInterface;
import com.example.sep4_android.network.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CO2Repository {
    private static CO2Repository instance;
    private final MutableLiveData<ArrayList<CO2>> co2s;

    private CO2Repository() {
        co2s = new MutableLiveData<>();
    }

    public static CO2Repository getInstance() {
        if (instance == null)
        {
            instance = new CO2Repository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<CO2>> getCo2s()
    {
        DataRetrieveInterface apiService = ServiceGenerator.getRetrofitInstance().create(DataRetrieveInterface.class);
        Call<ArrayList<CO2>> call = apiService.getCO2s();
        call.enqueue(new Callback<ArrayList<CO2>>() {
            @Override
            public void onResponse(Call<ArrayList<CO2>> call, Response<ArrayList<CO2>> response) {
                if (response.isSuccessful())
                {
                    co2s.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CO2>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return co2s;
    }
}

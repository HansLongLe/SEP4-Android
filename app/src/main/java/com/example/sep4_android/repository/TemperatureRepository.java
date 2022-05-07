package com.example.sep4_android.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.CO2;
import com.example.sep4_android.model.Temperature;
import com.example.sep4_android.network.DataRetrieveInterface;
import com.example.sep4_android.network.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemperatureRepository {
    private static TemperatureRepository instance;
    private final MutableLiveData<ArrayList<Temperature>> temperatureData;

    public TemperatureRepository() {
        temperatureData = new MutableLiveData<>();
    }

    public static TemperatureRepository getInstance() {
        if (instance == null)
        {
            instance = new TemperatureRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Temperature>> getTemperatureData()
    {
        DataRetrieveInterface apiService = ServiceGenerator.getRetrofitInstance().create(DataRetrieveInterface.class);
        Call<ArrayList<Temperature>> call = apiService.getAllTemperature();
        call.enqueue(new Callback<ArrayList<Temperature>>() {
            @Override
            public void onResponse(Call<ArrayList<Temperature>> call, Response<ArrayList<Temperature>> response) {
                temperatureData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Temperature>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return temperatureData;
    }
}

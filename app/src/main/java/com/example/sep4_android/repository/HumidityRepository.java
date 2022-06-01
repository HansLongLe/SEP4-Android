package com.example.sep4_android.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.Humidity;
import com.example.sep4_android.network.DataRetrieveInterface;
import com.example.sep4_android.network.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HumidityRepository {

    private static HumidityRepository instance;
    private final MutableLiveData<ArrayList<Humidity>> humidityData;

    private HumidityRepository() {
        humidityData = new MutableLiveData<>();
    }

    public static HumidityRepository getInstance() {
        if (instance == null)
        {
            instance = new HumidityRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Humidity>> getHumidityData()
    {
        DataRetrieveInterface apiService = ServiceGenerator.getRetrofitInstance().create(DataRetrieveInterface.class);
        Call<ArrayList<Humidity>> call = apiService.getHumidities();
        call.enqueue(new Callback<ArrayList<Humidity>>() {
            @Override
            public void onResponse(Call<ArrayList<Humidity>> call, Response<ArrayList<Humidity>> response) {
                if (response.isSuccessful())
                {
                    humidityData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Humidity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return humidityData;
    }
}

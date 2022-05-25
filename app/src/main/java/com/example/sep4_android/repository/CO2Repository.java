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

    public CO2Repository() {
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
        Call<ArrayList<Sensor>> call = apiService.getSensors();
        call.enqueue(new Callback<ArrayList<Sensor>>() {
            @Override
            public void onResponse(Call<ArrayList<Sensor>> call, Response<ArrayList<Sensor>> response) {
                if (response.isSuccessful())
                {
                    ArrayList<CO2> temp = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        temp.add(response.body().get(i).getCo2());
                        temp.get(i).setTime(response.body().get(i).getTime());
                    }
                    co2s.setValue(temp);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sensor>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return co2s;
    }
}

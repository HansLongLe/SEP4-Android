package com.example.sep4_android.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.CO2;
import com.example.sep4_android.model.Humidity;
import com.example.sep4_android.model.Sensor;
import com.example.sep4_android.network.DataRetrieveInterface;
import com.example.sep4_android.network.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HumidityRepository {

    private static HumidityRepository instance;
    private final MutableLiveData<ArrayList<Humidity>> humidityData;

    public HumidityRepository() {
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
        Call<ArrayList<Sensor>> call = apiService.getSensors();
        call.enqueue(new Callback<ArrayList<Sensor>>() {
            @Override
            public void onResponse(Call<ArrayList<Sensor>> call, Response<ArrayList<Sensor>> response) {
                if (response.isSuccessful())
                {
                    ArrayList<Humidity> temp = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        temp.add(response.body().get(i).getHumidity());
                        temp.get(i).setTime(response.body().get(i).getTime());
                    }
                    humidityData.setValue(temp);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sensor>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return humidityData;
    }
}

package com.example.sep4_android.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.Motion;
import com.example.sep4_android.network.DataRetrieveInterface;
import com.example.sep4_android.network.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotionRepository {
    private static MotionRepository instance;
    private final MutableLiveData<ArrayList<Motion>> motionData;

    public MotionRepository() {
        motionData = new MutableLiveData<>();
    }

    public static MotionRepository getInstance() {
        if (instance == null)
        {
            instance = new MotionRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Motion>> getMotionData()
    {
        DataRetrieveInterface apiService = ServiceGenerator.getRetrofitInstance().create(DataRetrieveInterface.class);
        Call<ArrayList<Motion>> call = apiService.getAllMotion();
        call.enqueue(new Callback<ArrayList<Motion>>() {
            @Override
            public void onResponse(Call<ArrayList<Motion>> call, Response<ArrayList<Motion>> response) {
                motionData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Motion>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return motionData;
    }
}

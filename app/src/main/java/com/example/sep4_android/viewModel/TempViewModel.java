package com.example.sep4_android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.CO2;
import com.example.sep4_android.model.Temperature;
import com.example.sep4_android.repository.CO2Repository;
import com.example.sep4_android.repository.TemperatureRepository;

import java.util.ArrayList;

public class TempViewModel extends AndroidViewModel {
    private TemperatureRepository temperatureRepository;

    public TempViewModel(@NonNull Application application) {
        super(application);
        temperatureRepository = TemperatureRepository.getInstance();
    }

    public LiveData<ArrayList<Temperature>> getTemperature()
    {
        return temperatureRepository.getTemperatureData();
    }
}
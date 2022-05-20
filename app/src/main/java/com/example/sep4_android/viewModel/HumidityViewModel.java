package com.example.sep4_android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Humidity;
import com.example.sep4_android.repository.HumidityRepository;

import java.util.ArrayList;

public class HumidityViewModel extends AndroidViewModel {
    private HumidityRepository humidityRepository;

    public HumidityViewModel(@NonNull Application application) {
        super(application);
        humidityRepository = HumidityRepository.getInstance();
    }

    public LiveData<ArrayList<Humidity>> getHumidity() {return humidityRepository.getHumidityData();}
}
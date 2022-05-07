package com.example.sep4_android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.CO2;
import com.example.sep4_android.repository.CO2Repository;

import java.util.ArrayList;

public class CO2ViewModel extends AndroidViewModel {
    private CO2Repository co2Repository;

    public CO2ViewModel(@NonNull Application application) {
        super(application);
        co2Repository = CO2Repository.getInstance();
    }

    public LiveData<ArrayList<CO2>> getCO2()
    {
        return co2Repository.getCo2s();
    }
}
package com.example.sep4_android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Motion;

import com.example.sep4_android.repository.MotionRepository;

import java.util.ArrayList;

public class MotionViewModel extends AndroidViewModel {
    private MotionRepository motionRepository;

    public MotionViewModel(@NonNull Application application)
    {
        super(application);
        motionRepository = MotionRepository.getInstance();
    }

    public LiveData<ArrayList<Motion>> getMotion(){return motionRepository.getMotionData();}
    // TODO: Implement the ViewModel
}
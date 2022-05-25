package com.example.sep4_android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Window;
import com.example.sep4_android.repository.WindowRepository;

import java.util.Date;
import java.sql.Timestamp;
import java.util.concurrent.BlockingQueue;

import retrofit2.Callback;

public class WindowViewModel extends AndroidViewModel {
    private WindowRepository windowRepository;

    public WindowViewModel(@NonNull Application application) {
        super(application);
        windowRepository = WindowRepository.getInstance();
    }

    public void setWindowStatus(boolean status)
    {
        Window window = new Window(new Timestamp(new Date().getTime()), status);
        windowRepository.sendWindowState(window);
    }

    public LiveData<Window> getWindowState()
    {
        return windowRepository.getWindowStatus();
    }
}

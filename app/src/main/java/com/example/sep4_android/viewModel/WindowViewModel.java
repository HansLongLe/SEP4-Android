package com.example.sep4_android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sep4_android.model.Window;
import com.example.sep4_android.repository.WindowRepository;

import java.util.Date;
import java.sql.Timestamp;

public class WindowViewModel extends AndroidViewModel {
    private WindowRepository windowRepository;

    public WindowViewModel(@NonNull Application application) {
        super(application);
        windowRepository = WindowRepository.getInstance();
    }

    public void setWindowStatus(boolean status)
    {
        Window window = new Window(1, new Timestamp(new Date().getTime()), status);
        windowRepository.sendWindowState(window);
    }
}

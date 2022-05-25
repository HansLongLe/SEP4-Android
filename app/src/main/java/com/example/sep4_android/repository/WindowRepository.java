package com.example.sep4_android.repository;

import com.example.sep4_android.model.Window;
import com.example.sep4_android.network.ServiceGenerator;
import com.example.sep4_android.network.WindowInterface;

import retrofit2.Call;

public class WindowRepository {
    private static WindowRepository instance;

    public static WindowRepository getInstance() {
        if (instance == null)
        {
            instance = new WindowRepository();
        }
        return instance;
    }

    public void sendWindowState(Window window){
        WindowInterface apiService = ServiceGenerator.getRetrofitInstance().create(WindowInterface.class);
        Call<String> response = apiService.sendWindowStatus(window);

    }
}

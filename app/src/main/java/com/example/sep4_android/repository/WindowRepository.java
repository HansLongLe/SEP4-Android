package com.example.sep4_android.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.Window;
import com.example.sep4_android.network.ServiceGenerator;
import com.example.sep4_android.network.WindowInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WindowRepository {
    private static WindowRepository instance;
    private MutableLiveData<Window> windowMutableLiveData;

    public static WindowRepository getInstance() {
        if (instance == null)
        {
            instance = new WindowRepository();
        }
        return instance;
    }

    private WindowRepository()
    {
        windowMutableLiveData = new MutableLiveData<>();
    }

    public void sendWindowState(Window window){
        WindowInterface apiService = ServiceGenerator.getRetrofitInstance().create(WindowInterface.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String jsonData = "{" +
                "\"timestamp\":\"" + sdf.format(window.getTimestamp()) + "\"," +
                "\"windowOpen\":" + window.getWindowOpen() +
                "}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonData);
        Call<Window> response = apiService.sendWindowStatus(jsonObject);
        response.enqueue(new Callback<Window>() {
            @Override
            public void onResponse(Call<Window> call, Response<Window> response) {
                if (response.isSuccessful())
                {
                    System.out.println(response.body().getWindowOpen());
                }
            }

            @Override
            public void onFailure(Call<Window> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Window> getWindowStatus()
    {
        WindowInterface apiService = ServiceGenerator.getRetrofitInstance().create(WindowInterface.class);
        Call<ArrayList<Window>> call = apiService.getWindowStatus();
        call.enqueue(new Callback<ArrayList<Window>>() {
            @Override
            public void onResponse(Call<ArrayList<Window>> call, Response<ArrayList<Window>> response) {
                if (response.isSuccessful())
                {
                    ArrayList<Window> windows = response.body();
                    windowMutableLiveData.setValue(windows.get(0));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Window>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return windowMutableLiveData;
    }
}

package com.example.sep4_android.model;

public class Temperature {
    String temperatureData;

    public Temperature (String temperatureData)
    {
        this.temperatureData = temperatureData;
    }

    public String getTemperatureData() {
        return temperatureData;
    }

    public void setTemperatureData(String temperatureData) {
        this.temperatureData = temperatureData;
    }
}

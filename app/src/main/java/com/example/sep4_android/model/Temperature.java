package com.example.sep4_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("temperatureId")
    @Expose
    private Integer temperatureId;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    private String time;


    public Temperature() {
    }

    public Temperature(Integer temperatureId, Double temperature, String time) {
        super();
        this.temperatureId = temperatureId;
        this.temperature = temperature;
        this.time = time;
    }

    public Integer getTemperatureId() {
        return temperatureId;
    }

    public void setTemperatureId(Integer temperatureId) {
        this.temperatureId = temperatureId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

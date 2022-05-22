package com.example.sep4_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Temperature {
    @SerializedName("temperatureId")
    @Expose
    private Integer temperatureId;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    private Timestamp time;


    public Temperature() {
    }

    public Temperature(Integer temperatureId, Double temperature, Timestamp time) {
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

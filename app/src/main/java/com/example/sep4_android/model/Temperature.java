package com.example.sep4_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Temperature {

    @SerializedName("time")
    @Expose
    private Timestamp time;
    @SerializedName("temperatureId")
    @Expose
    private Integer temperatureId;
    @SerializedName("temperature")
    @Expose
    private Double temperature;

    /**
     * No args constructor for use in serialization
     *
     */
    public Temperature() {
    }

    /**
     *
     * @param temperature
     * @param time
     * @param temperatureId
     */
    public Temperature(Timestamp time, Integer temperatureId, Double temperature) {
        super();
        this.time = time;
        this.temperatureId = temperatureId;
        this.temperature = temperature;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
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
}

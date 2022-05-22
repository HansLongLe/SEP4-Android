package com.example.sep4_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.sql.Timestamp;

public class Humidity {
    @SerializedName("humidityId")
    @Expose
    private Integer humidityId;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    private Timestamp time;

    public Humidity() {
    }

    public Humidity(Integer humidityId, Double humidity, Timestamp time) {
        super();
        this.humidityId = humidityId;
        this.humidity = humidity;
        this.time = time;
    }

    public Integer getHumidityId() {
        return humidityId;
    }

    public void setHumidityId(Integer humidityId) {
        this.humidityId = humidityId;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

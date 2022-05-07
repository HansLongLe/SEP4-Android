package com.example.sep4_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Humidity {
    @SerializedName("humidityId")
    @Expose
    private Integer humidityId;
    @SerializedName("humidity")
    @Expose
    private Double humidity;

    public Humidity() {
    }

    public Humidity(Integer humidityId, Double humidity) {
        super();
        this.humidityId = humidityId;
        this.humidity = humidity;
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
}

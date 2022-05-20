package com.example.sep4_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sensor {
    @SerializedName("temperature")
    @Expose
    private Temperature temperature;
    @SerializedName("humidity")
    @Expose
    private Humidity humidity;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("sensorId")
    @Expose
    private Integer sensorId;
    @SerializedName("co2")
    @Expose
    private CO2 co2;

    public Sensor() {
    }


    public Sensor(Temperature temperature, Humidity humidity, String time, Integer sensorId, CO2 co2) {
        super();
        this.temperature = temperature;
        this.humidity = humidity;
        this.time = time;
        this.sensorId = sensorId;
        this.co2 = co2;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public CO2 getCo2() {
        return co2;
    }

    public void setCo2(CO2 co2) {
        this.co2 = co2;
    }
}

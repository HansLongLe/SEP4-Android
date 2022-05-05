package com.example.sep4_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CO2 {
    @SerializedName("co2Id")
    @Expose
    private int co2Id;
    @SerializedName("co2Level")
    @Expose
    private int co2Level;

    public CO2(int co2Id, int co2Level)
    {
        this.co2Id = co2Id;
        this.co2Level = co2Level;
    }

    public int getCo2Level() {
        return co2Level;
    }

    public void setCo2Level(int co2Level) {
        this.co2Level = co2Level;
    }

    public int getCo2Id() {
        return co2Id;
    }

    public void setCo2Id(int co2Id) {
        this.co2Id = co2Id;
    }
}

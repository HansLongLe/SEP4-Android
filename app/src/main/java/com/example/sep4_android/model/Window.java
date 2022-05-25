package com.example.sep4_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;

public class Window implements Serializable {

    @SerializedName("windowId")
    @Expose
    private Integer windowId;
    @SerializedName("timestamp")
    @Expose
    private Timestamp timestamp;
    @SerializedName("windowOpen")
    @Expose
    private Boolean windowOpen;


    public Window() {
    }


    public Window(Timestamp timestamp, Boolean windowOpen) {
        this.timestamp = timestamp;
        this.windowOpen = windowOpen;
    }

    public Integer getWindowId() {
        return windowId;
    }

    public void setWindowId(Integer windowId) {
        this.windowId = windowId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getWindowOpen() {
        return windowOpen;
    }

    public void setWindowOpen(Boolean windowOpen) {
        this.windowOpen = windowOpen;
    }

}

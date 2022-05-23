package com.example.sep4_android.model;

public class AuthStatus {
    private String msg, msgColor, status;

    public AuthStatus(String status, String msg, String msgColor) {
        this.status = status;
        this.msg = msg;
        this.msgColor = msgColor;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsgColor() {
        return msgColor;
    }

    public String getStatus() {
        return status;
    }
}

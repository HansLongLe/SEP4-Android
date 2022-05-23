package com.example.sep4_android;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        FirebaseDatabase.getInstance("https://smart-gym-c02ac-default-rtdb.europe-west1.firebasedatabase.app/");
    }

    public static Context getContext() {
        return mContext;
    }
}

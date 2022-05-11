package com.example.sep4_android;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance("https://smart-gym-c02ac-default-rtdb.europe-west1.firebasedatabase.app/");
    }
}

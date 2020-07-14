package com.tdn.qurban.core;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);

    }
}

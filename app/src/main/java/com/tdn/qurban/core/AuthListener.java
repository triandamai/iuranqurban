package com.tdn.qurban.core;

import androidx.annotation.NonNull;

import com.tdn.domain.model.userModel;

public interface AuthListener {
    void onStart();
    void onSuccess(@NonNull String message, userModel data);
    void onError(@NonNull String message);
}

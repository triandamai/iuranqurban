package com.tdn.qurban.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tdn.qurban.auth.LoginViewModel;
import com.tdn.qurban.nasabah.ui.home.HomeFragment;
import com.tdn.qurban.nasabah.ui.home.HomeViewModel;

public class VMFactory implements ViewModelProvider.Factory {
    private Context context;
    private ActionListener listener;
    private AuthListener authListener;

    public VMFactory() {


    }

    public VMFactory(Context context, ActionListener actionListener) {

        this.context = context;
        this.listener = actionListener;
    }

    public VMFactory(Context context, AuthListener actionListener) {

        this.context = context;
        this.authListener = actionListener;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(context, authListener);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel();
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}

package com.tdn.qurban.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tdn.qurban.admin.ui.detailnasabah.DetailNasabahViewModel;
import com.tdn.qurban.auth.LoginViewModel;
import com.tdn.qurban.nasabah.ui.aktivasiakun.AktivasiAkunViewModel;
import com.tdn.qurban.nasabah.ui.home.HomeFragment;
import com.tdn.qurban.nasabah.ui.home.HomeViewModel;
import com.tdn.qurban.nasabah.ui.konfirmasipembayaran.KonfirmasiPembayaran;
import com.tdn.qurban.nasabah.ui.konfirmasipembayaran.KonfirmasiPembayaranViewModel;
import com.tdn.qurban.nasabah.ui.notifikasi.NotifikasiNasabah;
import com.tdn.qurban.nasabah.ui.notifikasi.NotifikasiNasabahViewModel;

public class VMFactory implements ViewModelProvider.Factory {
    private Context context;
    private ActionListener listener;
    private AuthListener authListener;
    private String id;

    public VMFactory() {


    }

    public VMFactory(Context context) {

        this.context = context;

    }

    public VMFactory(Context context, ActionListener actionListener) {

        this.context = context;
        this.listener = actionListener;
    }

    public VMFactory(Context context, AuthListener actionListener) {

        this.context = context;
        this.authListener = actionListener;
    }

    public VMFactory(Context context, String s) {
        this.context = context;
        this.id = s;

    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(context, authListener);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel();
        } else if (modelClass.isAssignableFrom(KonfirmasiPembayaran.class)) {
            return (T) new KonfirmasiPembayaranViewModel(context, listener);
        } else if (modelClass.isAssignableFrom(AktivasiAkunViewModel.class)) {
            return (T) new AktivasiAkunViewModel(context, listener);
        } else if (modelClass.isAssignableFrom(NotifikasiNasabahViewModel.class)) {
            return (T) new NotifikasiNasabahViewModel(context);
        } else if (modelClass.isAssignableFrom(DetailNasabahViewModel.class)) {
            return (T) new DetailNasabahViewModel(context, id);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}

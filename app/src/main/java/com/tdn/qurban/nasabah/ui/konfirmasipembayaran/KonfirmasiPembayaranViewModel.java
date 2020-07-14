package com.tdn.qurban.nasabah.ui.konfirmasipembayaran;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.qurban.core.ActionListener;

public class KonfirmasiPembayaranViewModel extends ViewModel {
    private Context context;
    private ActionListener listener;
    public MutableLiveData<String> nominal = new MutableLiveData<>();
    public MutableLiveData<String> ket = new MutableLiveData<>();

    public KonfirmasiPembayaranViewModel(Context context, ActionListener listener) {
        this.context = context;
        this.listener = listener;
        this.nominal.setValue("");
        this.ket.setValue("");
    }

    public void simpan() {
        listener.onStart();
    }
}
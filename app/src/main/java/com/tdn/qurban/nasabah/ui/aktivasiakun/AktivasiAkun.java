package com.tdn.qurban.nasabah.ui.aktivasiakun;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.qurban.R;

public class AktivasiAkun extends Fragment {

    private AktivasiAkunViewModel mViewModel;

    public static AktivasiAkun newInstance() {
        return new AktivasiAkun();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.aktivasi_akun_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AktivasiAkunViewModel.class);
        // TODO: Use the ViewModel
    }

}
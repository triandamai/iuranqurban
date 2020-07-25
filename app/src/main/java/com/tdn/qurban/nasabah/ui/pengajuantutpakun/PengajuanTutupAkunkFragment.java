package com.tdn.qurban.nasabah.ui.pengajuantutpakun;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.qurban.R;

public class PengajuanTutupAkunkFragment extends Fragment {

    private PengajuanTutupAkunkViewModel mViewModel;

    public static PengajuanTutupAkunkFragment newInstance() {
        return new PengajuanTutupAkunkFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pengajuan_tutup_akunk_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PengajuanTutupAkunkViewModel.class);
        // TODO: Use the ViewModel
    }

}
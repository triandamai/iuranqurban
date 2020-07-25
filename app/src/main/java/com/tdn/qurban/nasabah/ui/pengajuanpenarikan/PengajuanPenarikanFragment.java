package com.tdn.qurban.nasabah.ui.pengajuanpenarikan;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.qurban.R;

public class PengajuanPenarikanFragment extends Fragment {

    private PengajuanPenarikanViewModel mViewModel;

    public static PengajuanPenarikanFragment newInstance() {
        return new PengajuanPenarikanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pengajuan_penarikan_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PengajuanPenarikanViewModel.class);
        // TODO: Use the ViewModel
    }

}
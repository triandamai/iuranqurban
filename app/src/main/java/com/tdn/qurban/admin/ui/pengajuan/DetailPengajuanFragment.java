package com.tdn.qurban.admin.ui.pengajuan;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.qurban.R;

public class DetailPengajuanFragment extends Fragment {

    private DetailPengajuanViewModel mViewModel;

    public static DetailPengajuanFragment newInstance() {
        return new DetailPengajuanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_pengajuan_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailPengajuanViewModel.class);
        // TODO: Use the ViewModel
    }

}
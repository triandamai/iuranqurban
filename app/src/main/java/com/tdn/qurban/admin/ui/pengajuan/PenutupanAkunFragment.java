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

public class PenutupanAkunFragment extends Fragment {

    private PenutupanAkunViewModel mViewModel;

    public static PenutupanAkunFragment newInstance() {
        return new PenutupanAkunFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.penutupan_akun_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PenutupanAkunViewModel.class);
        // TODO: Use the ViewModel
    }

}
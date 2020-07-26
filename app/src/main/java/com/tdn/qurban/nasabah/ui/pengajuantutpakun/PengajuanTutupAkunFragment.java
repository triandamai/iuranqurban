package com.tdn.qurban.nasabah.ui.pengajuantutpakun;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.qurban.R;
import com.tdn.qurban.databinding.PengajuanTutupAkunFragmentBinding;

public class PengajuanTutupAkunFragment extends Fragment {

    private PengajuanTutupAkunViewModel mViewModel;
    private PengajuanTutupAkunFragmentBinding binding;

    public static PengajuanTutupAkunFragment newInstance() {
        return new PengajuanTutupAkunFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.pengajuan_tutup_akun_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(PengajuanTutupAkunViewModel.class);
        onClick();
        return binding.getRoot();
    }

    private void onClick() {
        binding.btnPickImage.setOnClickListener(v -> {
            Snackbar.make(binding.getRoot(), "Maaf Aksi Belum Bisa digunakan", BaseTransientBottomBar.LENGTH_LONG).show();
        });
    }


}
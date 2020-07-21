package com.tdn.qurban.admin.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.tdn.domain.model.notifikasiModel;
import com.tdn.domain.model.userModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.FragmentHomeAdminBinding;
import com.tdn.qurban.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeAdminBinding binding;
    private AdapterNotifikasiHomeAdmin adapterNotifikasiHomeAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_home_admin, container, false);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        adapterNotifikasiHomeAdmin = new AdapterNotifikasiHomeAdmin(adapterClicked);
        binding.rv.setAdapter(adapterNotifikasiHomeAdmin);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(homeViewModel);
    }

    private void observe(HomeViewModel homeViewModel) {
        homeViewModel.getNasabahAktif().observe(getViewLifecycleOwner(), integer -> {
            binding.jmlAktif.setText("" + integer);
        });
        homeViewModel.getNasabahNonAktif().observe(getViewLifecycleOwner(), integer -> {
            binding.jumlahNonaktif.setText("" + integer);
        });
        homeViewModel.getNotifikasiTabungan().observe(getViewLifecycleOwner(), notifikasiModels -> {
            if (notifikasiModels != null) {
                adapterNotifikasiHomeAdmin.setData(notifikasiModels);
            }

        });
        homeViewModel.getUserModelMutableLiveData().observe(getViewLifecycleOwner(), userModel -> {
            binding.tvNamaAdmin.setText("" + userModel.getNama());
        });
    }

    private AdapterClicked adapterClicked = posisi -> {

    };
}
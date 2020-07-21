package com.tdn.qurban.nasabah.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tdn.qurban.R;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private AdapterMyTabungan adapterMyTabungan;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_home, container, false);
        homeViewModel = new ViewModelProvider(this, new VMFactory()).get(HomeViewModel.class);
        adapterMyTabungan = new AdapterMyTabungan();
        binding.rv.setAdapter(adapterMyTabungan);


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(homeViewModel);
    }

    private void observe(HomeViewModel homeViewModel) {
        homeViewModel.getTabunganDatas().observe(getViewLifecycleOwner(), tabunganModels -> {
            if (tabunganModels != null) {
                adapterMyTabungan.setData(tabunganModels);
            }
        });
        homeViewModel.getSaldoDatas().observe(getViewLifecycleOwner(), saldoModel -> {
            if (saldoModel != null) {
                binding.tvSaldo.setText("Rp " + saldoModel.getJumlah());
            } else {
                binding.tvSaldo.setText("Rp 0 ");
            }
        });

        homeViewModel.getIsActive().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean != null) {
                if (aBoolean) {
                    binding.tvStatus.setText("Terverifikasi");
                } else {
                    binding.tvStatus.setText("Akun Belum Aktif");
                }
            }
        });
    }
}
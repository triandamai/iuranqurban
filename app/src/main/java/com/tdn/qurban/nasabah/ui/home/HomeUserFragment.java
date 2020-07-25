package com.tdn.qurban.nasabah.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.FragmentHomeBinding;


public class HomeUserFragment extends Fragment {

    private HomeUserViewModel homeUserViewModel;
    private FragmentHomeBinding binding;
    private AdapterMyTabungan adapterMyTabungan;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_home, container, false);
        homeUserViewModel = new ViewModelProvider(this, new VMFactory()).get(HomeUserViewModel.class);
        adapterMyTabungan = new AdapterMyTabungan(getContext(), adapterClicked);
        binding.rv.showShimmer();
        binding.lyKosong.setVisibility(View.GONE);
        binding.rv.setAdapter(adapterMyTabungan);

        onClick();

        return binding.getRoot();
    }

    private void onClick() {
        binding.tvAktivasi.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_aktivasiakun);
        });
        binding.btnLihatHostory.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_tabungan);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(homeUserViewModel);
    }

    private void observe(HomeUserViewModel homeUserViewModel) {
        homeUserViewModel.listTabunganLiveData.observe(getViewLifecycleOwner(), tabunganModels -> {
            if (tabunganModels != null) {
                adapterMyTabungan.setData(tabunganModels);
            } else {
                binding.lyKosong.setVisibility(View.VISIBLE);
            }
        });
        homeUserViewModel.saldoModel.observe(getViewLifecycleOwner(), saldoModel -> {
            if (saldoModel != null) {
                binding.tvSaldo.setText("Rp " + saldoModel.getJumlah());
            } else {
                binding.tvSaldo.setText("Rp 0 ");
            }
        });

        homeUserViewModel.isUserActive.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean != null) {
                if (aBoolean) {
                    binding.tvStatus.setText("Terverifikasi");
                    binding.tvStatus.setTextColor(getResources().getColor(R.color.colorPrimary));
                    binding.tvAktivasi.setVisibility(View.GONE);
                } else {
                    binding.tvStatus.setText("Akun Belum Aktif");
                    binding.tvStatus.setTextColor(getResources().getColor(R.color.red));
                    binding.tvAktivasi.setVisibility(View.VISIBLE);
                }
            } else {
                binding.tvStatus.setText("Akun Belum Aktif");
                binding.tvStatus.setTextColor(getResources().getColor(R.color.red));
                binding.tvAktivasi.setVisibility(View.VISIBLE);
            }
        });
    }

    private AdapterClicked adapterClicked = posisi -> {

    };
}
package com.tdn.qurban.admin.ui.detailnasabah;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.data.pref.MyUser;
import com.tdn.qurban.R;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.DetailNasabahFragmentBinding;

public class DetailNasabahFragment extends Fragment {

    private DetailNasabahViewModel mViewModel;
    private DetailNasabahFragmentBinding binding;

    public static DetailNasabahFragment newInstance() {
        return new DetailNasabahFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.detail_nasabah_fragment, container, false);

        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), MyUser.getInstance(getContext()).getLastIdNasabah())).get(DetailNasabahViewModel.class);
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);

    }

    private void observe(DetailNasabahViewModel mViewModel) {
        mViewModel.getDetailUsers(MyUser.getInstance(getContext()).getLastIdNasabah()).observe(getViewLifecycleOwner(), userModel -> {
            if (userModel != null) {
                binding.tvNama.setText(userModel.getNama());
                binding.tvStatus.setText(userModel.getStatus());
                binding.tvAlamat.setText(userModel.getAlamat());
                binding.tvAhliwaris.setText(userModel.getNama_ahli_waris());
                binding.tvNik.setText(userModel.getNik());
                binding.tvTelpon.setText(userModel.getNo_hp());
            } else {
                Snackbar.make(binding.getRoot(), "Tidak bisa mengambil data user", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
            }
        });
        mViewModel.getRencanaModelMutableLiveData(MyUser.getInstance(getContext()).getLastIdNasabah()).observe(getViewLifecycleOwner(), rencanaModel -> {
            if (rencanaModel != null) {
                binding.tvJenishewan.setText(rencanaModel.getJenis());
                binding.tvJumlahHewan.setText(rencanaModel.getJumlah());
            } else {
                Snackbar.make(binding.getRoot(), "Tidak bisa mengambil data rencana tabungan", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
            }
        });
    }
}
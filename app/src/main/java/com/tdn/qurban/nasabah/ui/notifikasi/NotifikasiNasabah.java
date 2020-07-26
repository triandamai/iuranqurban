package com.tdn.qurban.nasabah.ui.notifikasi;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.NotifikasiNasabahFragmentBinding;

public class NotifikasiNasabah extends Fragment {

    private NotifikasiNasabahViewModel mViewModel;
    private AdapterNotifikasi adapterNotifikasi;
    private NotifikasiNasabahFragmentBinding binding;

    public static NotifikasiNasabah newInstance() {
        return new NotifikasiNasabah();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.notifikasi_nasabah_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory()).get(NotifikasiNasabahViewModel.class);
        binding.rv.showShimmer();
        binding.lyKosong.setVisibility(View.GONE);
        adapterNotifikasi = new AdapterNotifikasi(getContext(), adapterClicked);
        binding.rv.setAdapter(adapterNotifikasi);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private AdapterClicked adapterClicked = posisi -> {

    };

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(NotifikasiNasabahViewModel mViewModel) {
        mViewModel.notifikasiModelLiveData.observe(getViewLifecycleOwner(), notifikasiModels -> {
            if (notifikasiModels != null) {
                binding.lyKosong.setVisibility(View.GONE);
                adapterNotifikasi.setData(notifikasiModels);
            } else {

                binding.lyKosong.setVisibility(View.VISIBLE);
            }
        });
    }
}
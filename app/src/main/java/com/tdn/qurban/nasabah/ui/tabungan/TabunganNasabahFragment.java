package com.tdn.qurban.nasabah.ui.tabungan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.domain.model.TabunganModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.TabunganNasabahFragmentBinding;

import java.util.List;

public class TabunganNasabahFragment extends Fragment {

    private TabunganNasabahViewModel mViewModel;
    private TabunganNasabahFragmentBinding binding;
    private AdapterTabunganNasabah adapterTabunganNasabah;


    public static TabunganNasabahFragment newInstance() {
        return new TabunganNasabahFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.tabungan_nasabah_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory()).get(TabunganNasabahViewModel.class);
        binding.lyKosong.setVisibility(View.GONE);
        binding.rv.setVisibility(View.VISIBLE);
        adapterTabunganNasabah = new AdapterTabunganNasabah(getContext(), adapterClicked);
        binding.rv.setAdapter(adapterTabunganNasabah);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(TabunganNasabahViewModel mViewModel) {
        mViewModel.listLiveData.observe(getViewLifecycleOwner(), tabunganModels -> {

            if (tabunganModels != null) {

                binding.lyKosong.setVisibility(View.GONE);
                adapterTabunganNasabah.setData(tabunganModels);
                adapterTabunganNasabah.notifyDataSetChanged();
                binding.tvJml.setText(String.valueOf(tabunganModels.size()) + " Riwayat transaksi");
            } else {
                binding.lyKosong.setVisibility(View.VISIBLE);
                binding.rv.setVisibility(View.GONE);
                binding.tvJml.setText("0 Riwayat Transaksi");
            }
        });

    }

    private AdapterClicked adapterClicked = posisi -> {

    };
}
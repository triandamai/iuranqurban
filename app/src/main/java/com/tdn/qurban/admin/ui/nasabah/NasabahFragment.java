package com.tdn.qurban.admin.ui.nasabah;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.domain.model.userModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.NasabahFragmentBinding;

import java.util.List;

public class NasabahFragment extends Fragment {

    private NasabahViewModel mViewModel;
    private AdapterNasabah adapterNasabah;
    private NasabahFragmentBinding binding;

    public static NasabahFragment newInstance() {
        return new NasabahFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.nasabah_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(NasabahViewModel.class);
        adapterNasabah = new AdapterNasabah(adapterClicked);
        binding.rv.setAdapter(adapterNasabah);
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(NasabahViewModel mViewModel) {
        mViewModel.getUseListLiveData().observe(getViewLifecycleOwner(), userModels -> {
            adapterNasabah.setData(userModels);
        });
    }

    private AdapterClicked adapterClicked = posisi -> {

    };
}
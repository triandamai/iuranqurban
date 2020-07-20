package com.tdn.qurban.admin.ui.nasabah;

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

import com.tdn.qurban.R;
import com.tdn.qurban.databinding.NasabahFragmentBinding;

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
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NasabahViewModel.class);
        // TODO: Use the ViewModel
    }

}
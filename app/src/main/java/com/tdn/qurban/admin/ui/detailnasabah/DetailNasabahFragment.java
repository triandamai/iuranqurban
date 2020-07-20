package com.tdn.qurban.admin.ui.detailnasabah;

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

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailNasabahViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getDetailUsers().observe(getViewLifecycleOwner(), userModel -> {

        });
    }
}
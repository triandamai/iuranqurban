package com.tdn.qurban.admin.ui.pengajuan;

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
import com.tdn.qurban.databinding.DetailPengajuanFragmentBinding;

public class DetailPengajuanDanaFragment extends Fragment {
    private DetailPengajuanFragmentBinding binding;

    private DetailPengajuanDanaViewModel mViewModel;

    public static DetailPengajuanDanaFragment newInstance() {
        return new DetailPengajuanDanaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.detail_pengajuan_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailPengajuanDanaViewModel.class);
        // TODO: Use the ViewModel
    }

}
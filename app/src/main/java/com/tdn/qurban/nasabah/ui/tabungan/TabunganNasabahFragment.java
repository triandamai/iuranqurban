package com.tdn.qurban.nasabah.ui.tabungan;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.qurban.R;

public class TabunganNasabahFragment extends Fragment {

    private TabunganNasabahViewModel mViewModel;

    public static TabunganNasabahFragment newInstance() {
        return new TabunganNasabahFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tabungan_nasabah_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TabunganNasabahViewModel.class);
        // TODO: Use the ViewModel
    }

}
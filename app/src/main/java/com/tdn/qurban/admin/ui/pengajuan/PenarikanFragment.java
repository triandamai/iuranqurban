package com.tdn.qurban.admin.ui.pengajuan;

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

import com.tdn.domain.model.TarikDanaModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.PenarikanFragmentBinding;

import java.util.List;

public class PenarikanFragment extends Fragment {

    private PenarikanViewModel mViewModel;
    private PenarikanFragmentBinding binding;
    private AdapterPenarikanDana adapterPenarikanDana;

    public static PenarikanFragment newInstance() {
        return new PenarikanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PenarikanViewModel.class);

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.penarikan_fragment, container, false);
        adapterPenarikanDana = new AdapterPenarikanDana(posisi -> {

        });
        binding.rv.setAdapter(adapterPenarikanDana);
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);

    }

    private void observe(PenarikanViewModel mViewModel) {
        mViewModel.getTarikDana().observe(getViewLifecycleOwner(), tarikDanaModels -> {
            if (tarikDanaModels != null) {
                adapterPenarikanDana.setData(tarikDanaModels);
            }
        });
    }
}
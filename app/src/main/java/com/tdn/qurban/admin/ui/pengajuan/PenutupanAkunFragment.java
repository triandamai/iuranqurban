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

import com.tdn.domain.model.TutupAkunModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.PenutupanAkunFragmentBinding;

import java.util.List;

public class PenutupanAkunFragment extends Fragment {

    private PenutupanAkunViewModel mViewModel;
    private PenutupanAkunFragmentBinding binding;
    private AdapterPenutupanAkun adapterPenutupanAkun;

    public static PenutupanAkunFragment newInstance() {
        return new PenutupanAkunFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PenutupanAkunViewModel.class);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.penutupan_akun_fragment, container, false);
        adapterPenutupanAkun = new AdapterPenutupanAkun(posisi -> {

        }, getContext());
        binding.rv.setAdapter(adapterPenutupanAkun);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(PenutupanAkunViewModel mViewModel) {
        mViewModel.getTutupAkunModel().observe(getViewLifecycleOwner(), tutupAkunModels -> {
            if (tutupAkunModels != null) {
                adapterPenutupanAkun.setData(tutupAkunModels);
            }
        });
    }
}
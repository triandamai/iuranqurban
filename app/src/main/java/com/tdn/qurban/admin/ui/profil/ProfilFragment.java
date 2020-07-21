package com.tdn.qurban.admin.ui.profil;

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
import com.tdn.qurban.databinding.ProfilFragmentBinding;

public class ProfilFragment extends Fragment {

    private ProfilViewModel mViewModel;
    private ProfilFragmentBinding binding;

    public static ProfilFragment newInstance() {
        return new ProfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.profil_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ProfilViewModel.class);
        return binding.getRoot();

    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(ProfilViewModel mViewModel) {
        mViewModel.getUserModelMutableLiveData().observe(getViewLifecycleOwner(), userModel -> {
            if (userModel != null) {
                binding.tvNama.setText(userModel.getNama());
                binding.tvLevel.setText(userModel.getLevel());
            }
        });
    }
}
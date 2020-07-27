package com.tdn.qurban.nasabah.ui.profil;

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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.tdn.qurban.databinding.ProfilNasabahFragmentBinding;

public class ProfilNasabahFragment extends Fragment {

    private ProfilNasabahViewModel mViewModel;
    private ProfilNasabahFragmentBinding binding;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static ProfilNasabahFragment newInstance() {
        return new ProfilNasabahFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.profil_nasabah_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ProfilNasabahViewModel.class);
        onCLick();
        return binding.getRoot();
    }

    private void onCLick() {
//        binding.btnLogout.setOnClickListener(v -> {
//            Snackbar.make(binding.getRoot(), "Maaf Aksi Belum Bisa digunakan", BaseTransientBottomBar.LENGTH_LONG).show();
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(ProfilNasabahViewModel mViewModel) {
        mViewModel.userModelLiveData.observe(getViewLifecycleOwner(), userModel -> {
            if (userModel != null) {
                binding.tvNama.setText(userModel.getNama());
                binding.tvLevel.setText(userModel.getLevel());
                Picasso.get().load(firebaseAuth.getCurrentUser().getPhotoUrl())
                        .into(binding.ivProfil);
            } else {
                Snackbar.make(binding.getRoot(), "Maaf gagal mendapatkan detail", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
            }
        });
    }
}
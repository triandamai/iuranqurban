package com.tdn.qurban.nasabah.ui.konfirmasipembayaran;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tdn.qurban.R;
import com.tdn.qurban.core.ActionListener;
import com.tdn.qurban.core.ImagePickerActivity;
import com.tdn.qurban.core.MyFragment;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.KonfirmasiPembayaranFragmentBinding;

import java.util.List;

import static com.tdn.qurban.core.ImagePickerActivity.showImagePickerOptions;

public class KonfirmasiPembayaran extends MyFragment {

    private KonfirmasiPembayaranViewModel mViewModel;
    private KonfirmasiPembayaranFragmentBinding binding;

    public static KonfirmasiPembayaran newInstance() {
        return new KonfirmasiPembayaran();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.konfirmasi_pembayaran_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), actionListener)).get(KonfirmasiPembayaranViewModel.class);
        textWatcher();
        onClick();
        return binding.getRoot();
    }

    private void onClick() {
        binding.btnPickImage.setOnClickListener(v -> {
            Dexter.withActivity(getActivity())
                    .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                showImagePickerOptions(getContext(), new ImagePickerActivity.PickerOptionListener() {
                                    @Override
                                    public void onTakeCameraSelected() {
                                        launchCameraIntent();
                                    }

                                    @Override
                                    public void onChooseGallerySelected() {
                                        launchGalleryIntent();
                                    }
                                });

                            }

                            if (report.isAnyPermissionPermanentlyDenied()) {
                                showSettingsDialog();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    });
        });
        binding.btnSimpan.setOnClickListener(v -> {
            if (cekValidasi()) {
                mViewModel.simpan();
            } else {
                Snackbar.make(binding.getRoot(), "Isi semua data", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }

    private boolean cekValidasi() {
        return !TextUtils.isEmpty(binding.etNominal.getText().toString()) && !TextUtils.isEmpty(binding.etKeterangan.getText().toString());
    }

    private void textWatcher() {
        binding.etNominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.nominal.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etKeterangan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.ket.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(@NonNull String message) {
            Snackbar.make(binding.getRoot(), "Sukses : " + message, BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onError(@NonNull String message) {
            Snackbar.make(binding.getRoot(), "Gagal : " + message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };

}
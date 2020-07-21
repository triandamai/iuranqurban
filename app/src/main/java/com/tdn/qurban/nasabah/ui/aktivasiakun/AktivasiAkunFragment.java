package com.tdn.qurban.nasabah.ui.aktivasiakun;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
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
import com.tdn.qurban.databinding.AktivasiAkunFragmentBinding;

import java.io.IOException;
import java.util.List;

import static com.tdn.qurban.core.ImagePickerActivity.showImagePickerOptions;
import static com.tdn.qurban.core.MyFragment.REQUEST_IMAGE;

public class AktivasiAkunFragment extends MyFragment {

    private AktivasiAkunViewModel mViewModel;
    private AktivasiAkunFragmentBinding binding;

    public static AktivasiAkunFragment newInstance() {
        return new AktivasiAkunFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.aktivasi_akun_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), actionListener)).get(AktivasiAkunViewModel.class);
        onClick();
        return binding.getRoot();
    }

    private void onClick() {
        binding.btnPickImage.setOnClickListener(v -> {
            Dexter.withActivity(getActivity()).withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
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
            }).check();
        });
        binding.btnSimpan.setOnClickListener(v -> {
            mViewModel.simpan();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(AktivasiAkunViewModel mViewModel) {
        mViewModel.getProgress().observe(getViewLifecycleOwner(), s -> {
            if (s == null) {
                binding.tvNamafile.setText("0 %");
            } else {
                binding.tvNamafile.setText(s.toString());
            }
        });
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses..", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onError(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                    // loading profile image from local cache
                    mViewModel.foto.setValue(uri.getPath());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
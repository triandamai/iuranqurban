package com.tdn.qurban.admin.ui.jenishewan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.qurban.R;
import com.tdn.qurban.core.ActionListener;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.TambahHewanFragmentBinding;

public class TambahHewan extends Fragment {

    private TambahHewanViewModel mViewModel;
    private TambahHewanFragmentBinding binding;

    public static TambahHewan newInstance() {
        return new TambahHewan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.tambah_hewan_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), actionListener)).get(TambahHewanViewModel.class);
        onClick();
        Watcher();
        return binding.getRoot();

    }

    private void Watcher() {
        binding.etNamahewan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.namaHewan.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etNominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.nomminalHewan.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void onClick() {
        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cekValidasi()) {
                    mViewModel.simpan();
                } else {
                    Snackbar.make(binding.getRoot(), "Isi Semua Field!", BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean cekValidasi() {
        return !TextUtils.isEmpty(binding.etNamahewan.getText()) && !TextUtils.isEmpty(binding.etNominal.getText());
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses...", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_jenishewan);
                }
            }, 1000);
        }

        @Override
        public void onError(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();

        }
    };

}
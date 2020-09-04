package com.tdn.qurban.nasabah.ui.pengajuanpenarikan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.tdn.data.Const;
import com.tdn.domain.model.TarikDanaModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.ActionListener;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.PengajuanPenarikanFragmentBinding;

import org.w3c.dom.Text;

import java.util.Date;

public class PengajuanPenarikanFragment extends Fragment {

    private PengajuanPenarikanViewModel mViewModel;
    private PengajuanPenarikanFragmentBinding binding;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static PengajuanPenarikanFragment newInstance() {
        return new PengajuanPenarikanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.pengajuan_penarikan_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), new ActionListener() {
            @Override
            public void onStart() {
                Snackbar.make(binding.getRoot(), "Proses..", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
            }

            @Override
            public void onSuccess(@NonNull String message) {
                Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
            }

            @Override
            public void onError(@NonNull String message) {
                Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
            }
        })).get(PengajuanPenarikanViewModel.class);
        onClick();
        return binding.getRoot();
    }

    private void onClick() {
        binding.btnPickImage.setOnClickListener(v -> {
            if (cekvalidasi()) {
                TarikDanaModel tarikDanaModel = new TarikDanaModel();
                tarikDanaModel.setAdmin_acc(Const.PENGAJUAN_VERIFIKASI_NO);
                tarikDanaModel.setAtas_nama(binding.etAtasnama.getText().toString());
                tarikDanaModel.setCreated_at(new Date().getTime());
                tarikDanaModel.setDesc(binding.etKeterangan.getText().toString());
                tarikDanaModel.setNominal(Double.parseDouble(binding.etNominal.getText().toString()));
                tarikDanaModel.setNorek(binding.etNorek.getText().toString());
                tarikDanaModel.setTitle("Tarik Dana");
                tarikDanaModel.setUser_uid(firebaseAuth.getCurrentUser().getUid());
                tarikDanaModel.setUpdated_at(new Date().getTime());
                tarikDanaModel.setUser_acc(Const.PENGAJUAN_VERIFIKASI_YES);

                mViewModel.simpan(tarikDanaModel);
            } else {
                Snackbar.make(binding.getRoot(), "Isi semua field !", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }

    private boolean cekvalidasi() {
        return !TextUtils.isEmpty(binding.etAtasnama.getText().toString()) &&
                !TextUtils.isEmpty(binding.etKeterangan.getText().toString()) &&
                !TextUtils.isEmpty(binding.etNominal.getText().toString()) &&
                !TextUtils.isEmpty(binding.etNorek.getText().toString());
    }


}
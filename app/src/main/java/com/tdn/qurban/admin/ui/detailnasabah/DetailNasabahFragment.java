package com.tdn.qurban.admin.ui.detailnasabah;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.tdn.data.Const;
import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.NotifikasiModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.DetailNasabahFragmentBinding;

import java.util.Date;

public class DetailNasabahFragment extends Fragment {

    private DetailNasabahViewModel mViewModel;
    private DetailNasabahFragmentBinding binding;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static DetailNasabahFragment newInstance() {
        return new DetailNasabahFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.detail_nasabah_fragment, container, false);

        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), MyUser.getInstance(getContext()).getLastIdNasabah())).get(DetailNasabahViewModel.class);
        onClick();
        return binding.getRoot();
    }

    private void onClick() {
        binding.btnAktifasi.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setTitle("Info")
                    .setMessage("Aktivasi User ?")
                    .setPositiveButton("YA", (dialog, which) -> {
                        akivasi();
                    });
            builder.create();
            builder.show();

        });
    }

    private void akivasi() {

        String id = databaseReference.push().getKey();
        NotifikasiModel notifikasiModel = new NotifikasiModel();
        notifikasiModel.setId_content(MyUser.getInstance(getContext()).getLastIdNasabah());
        notifikasiModel.setId(id);
        notifikasiModel.setCreated_at(String.valueOf(new Date().getTime()));
        notifikasiModel.setTo_uid(MyUser.getInstance(getContext()).getLastIdNasabah());
        notifikasiModel.setFrom_uid(firebaseAuth.getCurrentUser().getUid());
        notifikasiModel.setTipe(Const.TIPE_NOTIF_AKTIVASI);
        notifikasiModel.setStatus(Const.STATUS_NOTIF_AKTIVASI_DITERIMA);
        notifikasiModel.setBody("Disetujui");

        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_NOTIF_USER)
                .child(MyUser.getInstance(getContext()).getLastIdNasabah())
                .child(id)
                .setValue(notifikasiModel);

        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(MyUser.getInstance(getContext()).getLastIdNasabah())
                .child(Const.CHILD_ORDERBYSTATUS)
                .setValue(Const.STATUS_USER_AKTIF)
                .addOnSuccessListener(aVoid -> {
                    databaseReference.child(Const.BASE_CHILD)
                            .child(Const.CHILD_NOTIF_ADMIN)
                            .child(MyUser.getInstance(getContext()).getLastIdNotif()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Snackbar.make(binding.getRoot(), "Berhasil", BaseTransientBottomBar.LENGTH_LONG).show();
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_nasabah);
                        }
                    });

                });
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);

    }

    private void observe(DetailNasabahViewModel mViewModel) {
        mViewModel.getDetailUsers(MyUser.getInstance(getContext()).getLastIdNasabah()).observe(getViewLifecycleOwner(), userModel -> {
            if (userModel != null) {
                binding.tvNama.setText("Nama Nasabah : " + userModel.getNama());
                if (userModel.getStatus().equalsIgnoreCase(Const.STATUS_USER_PENDING)) {
                    binding.tvStatus.setText("Menunggu Persetujuan");
                } else if (userModel.getStatus().equalsIgnoreCase(Const.STATUS_USER_AKTIF)) {
                    binding.tvStatus.setText("Aktis");
                } else {
                    binding.tvStatus.setText("Tidak Aktif");
                }

                binding.tvAlamat.setText("Alamat : " + userModel.getAlamat());
                binding.tvAhliwaris.setText("Nama Ahli Waris : " + userModel.getNama_ahli_waris());
                binding.tvNik.setText("Nik : " + userModel.getNik());
                binding.tvTelpon.setText("No telp : " + userModel.getNo_hp());
                if (userModel.getKartu_identitas().equals("kosong")) {
                    Snackbar.make(binding.getRoot(), "User Belum Mengirimkan Kartu Identitas", BaseTransientBottomBar.LENGTH_LONG).show();
                } else {
                    Picasso.get().load(userModel.getKartu_identitas()).into(binding.ivBukti);
                }
            } else {
                Snackbar.make(binding.getRoot(), "Tidak bisa mengambil data user", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
            }
        });
        mViewModel.getRencanaModelMutableLiveData(MyUser.getInstance(getContext()).getLastIdNasabah()).observe(getViewLifecycleOwner(), rencanaModel -> {
            if (rencanaModel != null) {
                binding.tvJenishewan.setText("Jenis Hewan Qurban : " + rencanaModel.getJenis_hewan());
                binding.tvJumlahHewan.setText("Jumlah Hewan Qurban : " + rencanaModel.getJumlah());
                binding.tvTargetnominal.setText("Target Nominal  : Rp " + rencanaModel.getTarget_nominal());
                binding.tvTempatpenyerahan.setText("Tempat Penyerahan : " + rencanaModel.getTempat_penyerahan());
            } else {
                Snackbar.make(binding.getRoot(), "Tidak bisa mengambil data rencana tabungan", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
            }
        });
        mViewModel.getSaldos(MyUser.getInstance(getContext()).getLastIdNasabah())
                .observe(getViewLifecycleOwner(), saldoModel -> {
                    if (saldoModel != null) {
                        binding.tvJumlahTabungan.setText("Saldo : Rp " + saldoModel.getJumlah());
                    } else {
                        binding.tvJumlahTabungan.setText("Saldo : Rp 0");
                    }
                });
    }
}
package com.tdn.qurban.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.hewanModel;
import com.tdn.domain.model.rencanaModel;
import com.tdn.qurban.R;
import com.tdn.qurban.databinding.ActivityRencanaQurbanBinding;
import com.tdn.qurban.nasabah.NasabahActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RencanaQurbanActivity extends AppCompatActivity {
    private ActivityRencanaQurbanBinding binding;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rencana_qurban);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
        firebaseAuth = FirebaseAuth.getInstance();
        builder = new AlertDialog.Builder(this);
        builder.create();
        onClick();
    }

    private void onClick() {
        binding.btnSimpan.setOnClickListener(v -> {
            if (cekValidasi()) {
                simpan();
            } else {
                Snackbar.make(binding.getRoot(), "Harap Isi Semua Data", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
        binding.etTempatpenyerahan.setOnClickListener(v -> {
            String[] tempat = {"Masjid Baitul Muhsinin", "Lainnya"};
            builder.setTitle("Pilih Lokasi Penyerahan");
            builder.setItems(tempat, (dialog, which) -> {

                binding.etTempatpenyerahan.setText(tempat[which]);

            });
            builder.show();

        });

        binding.etJenisbeli.setOnClickListener(v -> {
            String[] jenisbeli = {"Beli Sendiri", "Melalui Panitia"};
            builder.setTitle("Pilih Jenis Pembelian");
            builder.setItems(jenisbeli, (dialog, which) -> {
                binding.etJenisbeli.setText(jenisbeli[which]);
            });
            builder.show();
        });
        binding.etJenisQurban.setOnClickListener(v -> {
            databaseReference.child(Const.CHILD_HEWAN)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String[] hewan = {};
                                String[] nominal = {};
                                String[] id = {};
                                int idx = 0;
                                for (DataSnapshot s : snapshot.getChildren()) {
                                    hewanModel m = s.getValue(hewanModel.class);
                                    m.setId(s.getKey());
                                    hewan[idx] = m.getJenis();
                                    nominal[idx] = m.getNominal();
                                    id[idx] = m.getId();
                                    idx++;
                                }
                                builder.setTitle("Pilih Jenis Hewan");
                                builder.setItems(hewan, (dialog, which) -> {
                                    binding.etJenisQurban.setText(hewan[which]);
                                    binding.etTargetnominal.setText(nominal[which]);
                                });
                            } else {
                                Snackbar.make(binding.getRoot(), "Gagal : error ambil data", BaseTransientBottomBar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Snackbar.make(binding.getRoot(), "Gagal : " + error.getMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
                        }
                    });
        });
    }

    private boolean cekValidasi() {
        return !TextUtils.isEmpty(binding.etTempatpenyerahan.getText().toString()) &&
                !TextUtils.isEmpty(binding.etJenisbeli.getText().toString()) &&
                !TextUtils.isEmpty(binding.etJenisQurban.getText().toString()) &&
                !TextUtils.isEmpty(binding.etJumlah.getText().toString()) &&
                !TextUtils.isEmpty(binding.etTargetnominal.getText().toString());
    }

    private void simpan() {
        rencanaModel m = new rencanaModel();
        m.setUid(firebaseAuth.getCurrentUser().getUid());
        m.setUpdated_at(String.valueOf(new Date().getTime()));
        m.setTempat_penyerahan(binding.etTempatpenyerahan.getText().toString());
        m.setPembelian(binding.etJenisbeli.getText().toString());
        m.setTarget_nominal(binding.etTargetnominal.getText().toString());
        m.setJumlah(binding.etJumlah.getText().toString());
        m.setJenis(binding.etJenisQurban.getText().toString());
        m.setCreated_at(String.valueOf(new Date().getTime()));
        databaseReference.child(Const.CHILD_RENCANA)
                .child(firebaseAuth.getUid())
                .setValue(m).addOnSuccessListener(aVoid -> {
            startActivity(new Intent(RencanaQurbanActivity.this, NasabahActivity.class));
            finish();
        }).addOnFailureListener(e -> {
            Snackbar.make(binding.getRoot(), "Gagal : " + e.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
        });
    }
}
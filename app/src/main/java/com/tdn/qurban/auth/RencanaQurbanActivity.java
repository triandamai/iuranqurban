package com.tdn.qurban.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.tdn.data.Const.currency;

public class RencanaQurbanActivity extends AppCompatActivity {
    private ActivityRencanaQurbanBinding binding;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private AlertDialog.Builder builder;
    private double jml = 0;
    private String id_jenis = "";
    private boolean kelompok = false;
    private double globalnominal = 0;
    String[] tempat = {"Masjid Baitul Muhsinin", "Lainnya"};
    String[] jenisbeli = {"Beli Sendiri", "Melalui Panitia"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rencana_qurban);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
        firebaseAuth = FirebaseAuth.getInstance();
        builder = new AlertDialog.Builder(this);
        builder.create();
        onClick();
        Watcher();
    }

    private void Watcher() {
        binding.etJumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {

                } else {
                    if (!binding.etTargetnominal.getText().toString().isEmpty()) {

                        jml = globalnominal;
                        int qty = Integer.parseInt(binding.etJumlah.getText().toString());
                        double hasil = jml * qty;
                        binding.etTargetnominal.setText(currency(hasil));
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.rgBeli.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb.getTag().equals("kelompok")) {
                binding.etJumlah.setVisibility(View.GONE);
                if (globalnominal > 0) {
                    binding.etTargetnominal.setText(currency(globalnominal / 7));
                }
                kelompok = true;
            } else {
                binding.etJumlah.setVisibility(View.VISIBLE);
                if (globalnominal >= 0) {
                    binding.etTargetnominal.setText(currency(globalnominal));
                }
                kelompok = false;
            }
        });
    }

    private void onClick() {
        binding.btnSimpan.setOnClickListener(v -> {
            if (cekValidasi()) {
                Snackbar.make(binding.getRoot(), "Proses..", BaseTransientBottomBar.LENGTH_LONG).show();
                simpan();
            } else {
                Snackbar.make(binding.getRoot(), "Harap Isi Semua Data", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
        binding.etTempatpenyerahan.setOnClickListener(v -> {

            builder.setTitle("Pilih Lokasi Penyerahan");
            builder.setItems(tempat, (dialog, which) -> {

                binding.etTempatpenyerahan.setText(tempat[which]);

            });
            builder.show();

        });

        binding.etJenisbeli.setOnClickListener(v -> {

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
                                for (DataSnapshot s : snapshot.getChildren()) {
                                    hewanModel m = s.getValue(hewanModel.class);
                                    m.setId(s.getKey());
                                    Log.e("hewan", m.toString());
                                    m.setId(s.getKey());
                                    hewan = ArrayUtils.appendToArray(hewan, m.getJenis());
                                    nominal = ArrayUtils.appendToArray(nominal, m.getNominal());
                                    id = ArrayUtils.appendToArray(id, m.getId());

                                }
                                builder.setTitle("Pilih Jenis Hewan");
                                String[] finalHewan = hewan;
                                String[] finalNominal = nominal;
                                String[] finalId = id;
                                builder.setItems(hewan, (dialog, which) -> {
                                    globalnominal = Double.parseDouble(finalNominal[which]);
                                    binding.etJenisQurban.setText(finalHewan[which]);
                                    if (finalHewan[which].equalsIgnoreCase("sapi")) {
                                        binding.etTargetnominal.setEnabled(false);
                                        binding.tvSapi.setVisibility(View.VISIBLE);
                                        binding.lySapi.setVisibility(View.VISIBLE);
                                        if (kelompok) {
                                            binding.etTargetnominal.setText(currency(globalnominal / 7));
                                            jml = globalnominal / 7;
                                        } else {
                                            binding.etTargetnominal.setText(currency(globalnominal));
                                            jml = globalnominal;
                                        }
                                    } else {
                                        binding.etTargetnominal.setEnabled(true);
                                        binding.tvSapi.setVisibility(View.GONE);
                                        binding.lySapi.setVisibility(View.GONE);
                                        binding.etTargetnominal.setText(String.valueOf(globalnominal));
                                        jml = globalnominal;
                                    }


                                    id_jenis = finalId[which];
                                }).show();
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
                !TextUtils.isEmpty(binding.etTargetnominal.getText().toString());
    }

    private void simpan() {
        rencanaModel m = new rencanaModel();
        m.setUid(firebaseAuth.getCurrentUser().getUid());
        m.setJenis_hewan_id(id_jenis);
        m.setUpdated_at(String.valueOf(new Date().getTime()));
        m.setTempat_penyerahan(binding.etTempatpenyerahan.getText().toString());
        m.setJenis_pembelian(binding.etJenisbeli.getText().toString());
        m.setTarget_nominal(binding.etTargetnominal.getText().toString());
        if (kelompok) {
            m.setJumlah("1");
            m.setKelompok(kelompok);
        } else {
            m.setJumlah(binding.etJumlah.getText().toString());
            m.setKelompok(kelompok);
        }
        m.setJenis_hewan(binding.etJenisQurban.getText().toString());
        m.setCreated_at(String.valueOf(new Date().getTime()));

        databaseReference.child(Const.CHILD_RENCANA)
                .child(firebaseAuth.getUid())
                .setValue(m).addOnSuccessListener(aVoid -> {
            databaseReference
                    .child(Const.CHILD_USER)
                    .child(firebaseAuth.getCurrentUser().getUid())
                    .child(Const.CHILD_USER_RENCANA)
                    .setValue(Const.SUDAH_RENCANA).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    startActivity(new Intent(RencanaQurbanActivity.this, NasabahActivity.class));
                    finish();
                }
            });

        }).addOnFailureListener(e -> {
            Snackbar.make(binding.getRoot(), "Gagal : " + e.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
        });
    }
}
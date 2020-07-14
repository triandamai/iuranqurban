package com.tdn.qurban.auth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdn.data.Const;
import com.tdn.domain.model.userModel;
import com.tdn.qurban.R;
import com.tdn.qurban.databinding.ActivityRegistrasiBinding;

import java.util.Date;

public class RegistrasiActivity extends AppCompatActivity {
    private ActivityRegistrasiBinding binding;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private MaterialAlertDialogBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registrasi);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);

        binding.etJk.setText(Const.JK_LK);
        binding.etHubungan.setText(Const.HUBUNGAN_ANAK);

        builder = new MaterialAlertDialogBuilder(getApplicationContext(), R.style.dialog);

        builder.create();

        onCLick();
    }

    private void onCLick() {
        binding.etJk.setOnClickListener(v -> {
            String[] jeniskelamin = {Const.JK_LK, Const.JK_PR};
            builder.setTitle("Pilih Jneis Kelmain");
            builder.setItems(jeniskelamin, (dialog, which) -> {
                binding.etJk.setText(which);
            });
            builder.show();
        });
        binding.etHubungan.setOnClickListener(v -> {
            String[] hubungan = {Const.HUBUNGAN_ANAK, Const.HUBUNGAN_ORANGTUA, Const.HUBUNGAN_SAUDARA};
            builder.setTitle("Pilih hubungan dengan ahli waris");
            builder.setItems(hubungan, (dialog, which) -> {
                binding.etHubungan.setText(which);
            });
        });
        binding.btnSimpan.setOnClickListener(v -> {
            if (cekValidasi()) {
                simpan();
            } else {
                Snackbar.make(binding.getRoot(), "Harap Isi Semua Data", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }

    private boolean cekValidasi() {
        return !TextUtils.isEmpty(binding.etAlamat.getText().toString()) &&
                !TextUtils.isEmpty(binding.etHp.getText().toString()) &&
                !TextUtils.isEmpty(binding.etHubungan.getText().toString()) &&
                !TextUtils.isEmpty(binding.etJk.getText().toString()) &&
                !TextUtils.isEmpty(binding.etNama.getText().toString()) &&
                !TextUtils.isEmpty(binding.etNamaahliwaris.getText().toString()) &&
                !TextUtils.isEmpty(binding.etNik.getText().toString()) &&
                !TextUtils.isEmpty(binding.etNikahliwaris.getText().toString());
    }

    private void simpan() {
        userModel m = new userModel();
        m.setUid(firebaseAuth.getCurrentUser().getUid());
        m.setUpdated_at(String.valueOf(new Date().getTime()));
        m.setNo_hp(binding.etHp.getText().toString());
        m.setStatus(Const.STATUS_USER_PENDING);
        m.setNik(binding.etNik.getText().toString());
        m.setNama_ahli_waris(binding.etNamaahliwaris.getText().toString());
        m.setLevel(Const.USER_LEVEL_NASABAH);
        m.setKartu_identitas(Const.FILE_KOSONG);
        m.setJenis_kelamin(binding.etJk.getText().toString());
        m.setHubungan_dengan_ahli_waris(binding.etHubungan.getText().toString());
        m.setHp_wa(binding.etHp.getText().toString());
        m.setCreated_at(String.valueOf(new Date().getTime()));
        m.setAlamat(binding.etAlamat.getText().toString());

        databaseReference.child(Const.CHILD_USER)
                .child(firebaseAuth.getCurrentUser().getUid())
                .setValue(m).addOnSuccessListener(aVoid -> {
            Snackbar.make(binding.getRoot(), "Berhasil..", BaseTransientBottomBar.LENGTH_LONG).show();
            startActivity(new Intent(RegistrasiActivity.this, RencanaQurbanActivity.class));
            finish();
        }).addOnFailureListener(e -> {
            Snackbar.make(binding.getRoot(), "Gagal : " + e.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
        }).addOnCompleteListener(task -> {
            Snackbar.make(binding.getRoot(), "Berhasil..", BaseTransientBottomBar.LENGTH_LONG).show();
        });

    }


}
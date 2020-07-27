package com.tdn.qurban.admin.ui.tabungan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.tdn.data.Const;
import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.NotifikasiModel;
import com.tdn.domain.model.SaldoModel;
import com.tdn.domain.model.TabunganModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.DetailTabunganFragmentBinding;

import java.util.Date;

public class DetailTabunganFragment extends Fragment {

    private DetailTabunganViewModel mViewModel;
    private DetailTabunganFragmentBinding binding;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private TabunganModel tabunganModel;

    public static DetailTabunganFragment newInstance() {
        return new DetailTabunganFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.detail_tabungan_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext())).get(DetailTabunganViewModel.class);
        onClick();
        return binding.getRoot();
    }

    private void onClick() {
        binding.btnAktifasi.setOnClickListener(v -> {
            NotifikasiModel n = new NotifikasiModel();
            n.setBody("Tambah saldo di terima");
            n.setStatus(Const.STATUS_NOTIF_TAMBAHSALDO_DITERIMA);
            n.setTipe(Const.TIPE_NOTIF_TAMBAHSALDO);
            n.setFrom_uid(firebaseAuth.getCurrentUser().getUid());
            n.setBroad_to(tabunganModel.getUid());
            n.setCreated_at(String.valueOf(new Date().getTime()));
            n.setId_content(tabunganModel.getId());
            databaseReference.child(Const.BASE_CHILD)
                    .child(Const.CHILD_NOTIF_USER)
                    .child(tabunganModel.getUid())
                    .setValue(n);
            databaseReference.child(Const.BASE_CHILD)
                    .child(Const.CHILD_TABUNGAN)
                    .child(MyUser.getInstance(getContext()).getLastIdTabungan())
                    .child(Const.CHILD_ORDERBYSTATUS)
                    .setValue(Const.STATUS_NOTIF_TAMBAHSALDO_DITERIMA)
                    .addOnSuccessListener(aVoid -> {
                        databaseReference.child(Const.BASE_CHILD)
                                .child(Const.CHILD_SALDO)
                                .child(tabunganModel.getUid())
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        SaldoModel saldoModel = new SaldoModel();
                                        if (snapshot.exists()) {
                                            saldoModel = snapshot.getValue(SaldoModel.class);
                                            assert saldoModel != null;
                                            saldoModel.setUid(snapshot.getKey());
                                            double asli = saldoModel.getJumlah();
                                            double baru = Double.parseDouble(tabunganModel.getNominal());

                                            double total = asli + baru;
                                            SaldoModel s = new SaldoModel();
                                            s.setUid(tabunganModel.getUid());
                                            s.setJumlah(total);
                                            s.setLast_updated(String.valueOf(new Date().getTime()));
                                            databaseReference.child(Const.BASE_CHILD)
                                                    .child(Const.CHILD_SALDO)
                                                    .child(tabunganModel.getUid())
                                                    .setValue(s);

                                        } else {
                                            SaldoModel s = new SaldoModel();
                                            s.setUid(tabunganModel.getUid());
                                            s.setJumlah(Double.parseDouble(tabunganModel.getNominal()));
                                            s.setLast_updated(String.valueOf(new Date().getTime()));

                                            databaseReference.child(Const.BASE_CHILD)
                                                    .child(Const.CHILD_SALDO)
                                                    .child(tabunganModel.getUid())
                                                    .setValue(s);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        Snackbar.make(binding.getRoot(), "Diterima", BaseTransientBottomBar.LENGTH_LONG).show();
                    });

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailTabunganViewModel mViewModel) {
        mViewModel.getTabnganById(MyUser.getInstance(getContext()).getLastIdTabungan())
                .observe(getViewLifecycleOwner(), tabunganModel -> {
                    Log.e("tes notif", tabunganModel.toString());
                    if (tabunganModel != null) {

                        this.tabunganModel = tabunganModel;
                        binding.tvJumlah.setText(tabunganModel.getNominal());
                        binding.tvStatus.setText(tabunganModel.getStatus());
                        if (tabunganModel.getBukti().equals("kosong")) {

                        } else {
                            Picasso.get().load(tabunganModel.getBukti())
                                    .into(binding.ivBukti);
                        }
                    } else {
                        this.tabunganModel = new TabunganModel();
                    }
                });
    }
}
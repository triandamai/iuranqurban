package com.tdn.qurban.nasabah.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.FragmentHomeBinding;


public class HomeUserFragment extends Fragment {

    private HomeUserViewModel homeUserViewModel;
    private FragmentHomeBinding binding;
    private AdapterMyTabungan adapterMyTabungan;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_home, container, false);
        homeUserViewModel = new ViewModelProvider(this, new VMFactory()).get(HomeUserViewModel.class);
        adapterMyTabungan = new AdapterMyTabungan(getContext(), adapterClicked);
        binding.rv.showShimmer();
        binding.lyKosong.setVisibility(View.GONE);
        binding.rv.setAdapter(adapterMyTabungan);

        onClick();

        return binding.getRoot();
    }

    private void onClick() {
        binding.tvAktivasi.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_aktivasiakun);
        });
        binding.btnLihatHostory.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_tabungan);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(homeUserViewModel);
    }

    private void observe(HomeUserViewModel homeUserViewModel) {
        homeUserViewModel.listTabunganLiveData.observe(getViewLifecycleOwner(), tabunganModels -> {
            if (tabunganModels != null) {
                adapterMyTabungan.setData(tabunganModels);
            } else {
                binding.lyKosong.setVisibility(View.VISIBLE);
            }
        });
        homeUserViewModel.saldoModel.observe(getViewLifecycleOwner(), saldoModel -> {
            if (saldoModel != null) {
                binding.tvSaldo.setText("Rp " + saldoModel.getJumlah());
            } else {
                binding.tvSaldo.setText("Rp 0 ");
            }
        });

        homeUserViewModel.isUserActive.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean != null) {
                if (aBoolean) {
                    binding.tvStatus.setText("Terverifikasi");
                    binding.tvStatus.setTextColor(getResources().getColor(R.color.colorPrimary));
                    binding.tvAktivasi.setVisibility(View.GONE);
                } else {

                    binding.tvStatus.setTextColor(getResources().getColor(R.color.red));
                    binding.tvAktivasi.setVisibility(View.VISIBLE);
                    cekActivasi();
                }
            } else {

                binding.tvStatus.setTextColor(getResources().getColor(R.color.red));
                binding.tvAktivasi.setVisibility(View.VISIBLE);
                cekActivasi();
            }
        });
        homeUserViewModel.userModel.observe(getViewLifecycleOwner(), userModel -> {
            if (userModel != null)
                binding.tvNamaNasabah.setText("Nasabah " + userModel.getNama());
        });
    }

    private void cekActivasi() {
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_AKTIVASI)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            binding.tvStatus.setText("Menunggu Konfirmasi Admin");
                        } else {
                            binding.tvStatus.setText("Akun Belum Aktif");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private AdapterClicked adapterClicked = posisi -> {

    };
}
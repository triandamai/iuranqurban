package com.tdn.qurban.admin.ui.jenishewan;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdn.data.Const;
import com.tdn.data.pref.MyUser;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterActionClicked;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.JenisHewanFragmentBinding;

public class JenisHewaFragment extends Fragment {

    private JenisHewaViewModel mViewModel;
    private AdapterJenisHewan adapterJenisHewan;
    private JenisHewanFragmentBinding binding;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static JenisHewaFragment newInstance() {
        return new JenisHewaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.jenis_hewan_fragment, container, false);

        adapterJenisHewan = new AdapterJenisHewan(getContext(), new AdapterActionClicked() {
            @Override
            public void onAdd(int posisi) {

            }

            @Override
            public void onEdit(int posisi) {
                MyUser.getInstance(getContext()).setLastIdHewan(adapterJenisHewan.getFromPosition(posisi).getId());
                Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_tambahhewan);
            }

            @Override
            public void onDelete(int posisi) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Hapus hewan");
                builder.setMessage("Yakin Menghapus hewan" + adapterJenisHewan.getFromPosition(posisi).getJenis() + " ?");
                builder.setCancelable(true);
                builder.setPositiveButton("YA HAPUS", (dialog, which) -> {
                    databaseReference.child(Const.BASE_CHILD)
                            .child(Const.CHILD_HEWAN)
                            .child(adapterJenisHewan.getFromPosition(posisi).getId())
                            .removeValue();
                });

            }
        });
        binding.rv.setAdapter(adapterJenisHewan);

        onClick();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new VMFactory()).get(JenisHewaViewModel.class);
    }

    private void onClick() {
        binding.btnTambah.setOnClickListener(v -> {
            MyUser.getInstance(getContext()).setLastIdHewan(null);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_tambahhewan);
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(JenisHewaViewModel mViewModel) {
        binding.setIsLoading(false);
        mViewModel.getJenisHewan().observe(getViewLifecycleOwner(), hewanModels -> {

            if (hewanModels != null) {
                adapterJenisHewan.setData(hewanModels);
            }
        });
    }

}
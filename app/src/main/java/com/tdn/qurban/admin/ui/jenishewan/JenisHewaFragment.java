package com.tdn.qurban.admin.ui.jenishewan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.JenisHewanFragmentBinding;

public class JenisHewaFragment extends Fragment {

    private JenisHewaViewModel mViewModel;
    private AdapterJenisHewan adapterJenisHewan;
    private JenisHewanFragmentBinding binding;

    public static JenisHewaFragment newInstance() {
        return new JenisHewaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.jenis_hewan_fragment, container, false);

        adapterJenisHewan = new AdapterJenisHewan(getContext(), adapterClicked);
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

    private AdapterClicked adapterClicked = posisi -> {

    };
}
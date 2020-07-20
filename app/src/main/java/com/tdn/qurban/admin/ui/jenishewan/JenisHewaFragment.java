package com.tdn.qurban.admin.ui.jenishewan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.domain.model.hewanModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.JenisHewaFragmentBinding;

import java.util.List;

public class JenisHewaFragment extends Fragment {

    private JenisHewaViewModel mViewModel;
    private AdapterJenisHewan adapterJenisHewan;
    private JenisHewaFragmentBinding binding;

    public static JenisHewaFragment newInstance() {
        return new JenisHewaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.jenis_hewa_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new VMFactory()).get(JenisHewaViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(JenisHewaViewModel mViewModel) {
        mViewModel.getJenisHewaViewModel().observe(getViewLifecycleOwner(), hewanModels -> {

        });
    }
}
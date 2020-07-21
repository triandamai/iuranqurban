package com.tdn.qurban.admin.ui.tabungan;

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

import com.tdn.domain.model.TabunganModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.TabunganFragmentBinding;

import java.util.List;

public class TabunganFragment extends Fragment {

    private TabunganViewModel mViewModel;
    private TabunganFragmentBinding binding;
    private AdapterTabunganAdmin adapterTabunganAdmin;

    public static TabunganFragment newInstance() {
        return new TabunganFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.tabungan_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory()).get(TabunganViewModel.class);
        adapterTabunganAdmin = new AdapterTabunganAdmin(getContext(), adapterClicked);
        binding.rv.setAdapter(adapterTabunganAdmin);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(TabunganViewModel mViewModel) {
        mViewModel.getTabunganData().observe(getViewLifecycleOwner(), tabunganModels -> {
            if (tabunganModels != null) {
                adapterTabunganAdmin.setData(tabunganModels);
            }
        });
    }

    private AdapterClicked adapterClicked = posisi -> {

    };

}
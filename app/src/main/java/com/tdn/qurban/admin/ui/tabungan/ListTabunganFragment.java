package com.tdn.qurban.admin.ui.tabungan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.print.PageRange;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ListTabunganFragmentBinding;

import java.util.List;

public class ListTabunganFragment extends Fragment {

    private ListTabunganViewModel mViewModel;
    private ListTabunganFragmentBinding binding;
    private AdapterListTabunganAdmin adapterListTabunganAdmin;

    public static ListTabunganFragment newInstance() {
        return new ListTabunganFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.list_tabungan_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ListTabunganViewModel.class);
        adapterListTabunganAdmin = new AdapterListTabunganAdmin(getContext(), adapterClicked);
        binding.rv.setAdapter(adapterListTabunganAdmin);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(ListTabunganViewModel mViewModel) {
        mViewModel.getListtab().observe(getViewLifecycleOwner(), strings -> {
            if (strings != null) {
                adapterListTabunganAdmin.setData(strings);
            }
        });
    }

    private AdapterClicked adapterClicked = posisi -> {

    };
}
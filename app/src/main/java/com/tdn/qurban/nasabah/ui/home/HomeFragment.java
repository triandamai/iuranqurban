package com.tdn.qurban.nasabah.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdn.domain.model.tabunganModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_home, container, false);
        homeViewModel = new ViewModelProvider(this, new VMFactory()).get(HomeViewModel.class);


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(homeViewModel);
    }

    private void observe(HomeViewModel homeViewModel) {
        homeViewModel.getTabunganDatas().observe(getViewLifecycleOwner(), new Observer<List<tabunganModel>>() {
            @Override
            public void onChanged(List<tabunganModel> tabunganModels) {
                if (tabunganModels != null) {

                }
            }
        });
    }
}
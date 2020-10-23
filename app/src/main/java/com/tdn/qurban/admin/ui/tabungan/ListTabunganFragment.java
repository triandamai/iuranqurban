package com.tdn.qurban.admin.ui.tabungan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.HewanModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ListTabunganFragmentBinding;
import com.trian.singleadapter.SingleAdapter;
import com.trian.singleadapter.onEventClick;
import com.trian.singleadapter.onEventType;

import java.util.List;

public class ListTabunganFragment extends Fragment {

    private ListTabunganViewModel mViewModel;
    private ListTabunganFragmentBinding binding;
    private SingleAdapter<String> adapterListtabungan;
    private SingleAdapter<HewanModel> adspterHewan;

    public static ListTabunganFragment newInstance() {
        return new ListTabunganFragment();
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.list_tabungan_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ListTabunganViewModel.class);
        adspterHewan = new SingleAdapter<>(R.layout.item_chip_hewan, (eventType, payload, position) -> {
            mViewModel.getListtabungan(payload.getJenis());

            observeTabungan();
        });
        adapterListtabungan = new SingleAdapter<>(R.layout.item_listtabungan, new onEventClick<String>() {
            @Override
            public void onClick(onEventType eventType, String payload, int position) {
                MyUser.getInstance(getContext()).setLastIdNasabah(payload);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_tabungan);

            }
        });
        binding.rv.setAdapter(adapterListtabungan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.rvHewan.setLayoutManager(layoutManager);
        binding.rvHewan.setAdapter(adspterHewan);

        return binding.getRoot();
    }



    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getListtabungan(null);
        mViewModel.getListhewan();
        observe();
        observeTabungan();

    }
    private void observeTabungan( ) {
        mViewModel.tabunganList.observe(getViewLifecycleOwner(), strings -> {
            if (strings != null) {
                adapterListtabungan.setData(strings);
            }
        });
    }

    private void observe( ) {

        mViewModel.hewanModelList.observe(getViewLifecycleOwner(), hewanModels -> {
            if(hewanModels != null){
                adspterHewan.setData(hewanModels);
            }
        });

    }


}
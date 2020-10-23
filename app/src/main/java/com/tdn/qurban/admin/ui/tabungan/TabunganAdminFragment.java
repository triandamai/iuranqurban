package com.tdn.qurban.admin.ui.tabungan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.TabunganModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.TabunganFragmentBinding;
import com.trian.singleadapter.SingleAdapter;
import com.trian.singleadapter.onEventClick;
import com.trian.singleadapter.onEventType;

public class TabunganAdminFragment extends Fragment {

    private TabunganAdminViewModel mViewModel;
    private TabunganFragmentBinding binding;
    private SingleAdapter<TabunganModel> singleAdapter;

    public static TabunganAdminFragment newInstance() {
        return new TabunganAdminFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.tabungan_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext())).get(TabunganAdminViewModel.class);
        singleAdapter = new SingleAdapter<>(R.layout.item_tabungan, (eventType, payload, position) -> {
            MyUser.getInstance(getContext()).setLastIdTabungan(payload.getId());
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_detail_tabungan);
        });

        binding.rv.setAdapter(singleAdapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(TabunganAdminViewModel mViewModel) {
        binding.setIsLoading(false);
        mViewModel.tabunganData.observe(getViewLifecycleOwner(), tabunganModels -> {
            if (tabunganModels != null) {
                singleAdapter.setData(tabunganModels);
            }
        });
    }


}
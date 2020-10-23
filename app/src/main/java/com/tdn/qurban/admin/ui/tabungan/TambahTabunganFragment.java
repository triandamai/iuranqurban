package com.tdn.qurban.admin.ui.tabungan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
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
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.tdn.qurban.databinding.TambahTabunganFragmentBinding;
import com.trian.singleadapter.SingleAdapter;
import com.trian.singleadapter.onEventClick;
import com.trian.singleadapter.onEventType;

import java.util.List;

public class TambahTabunganFragment extends Fragment {

    private TambahTabunganViewModel mViewModel;
    private SingleAdapter<UserModel> adapter;
    private TambahTabunganFragmentBinding binding;

    public static TambahTabunganFragment newInstance() {
        return new TambahTabunganFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.tambah_tabungan_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(TambahTabunganViewModel.class);
        adapter = new SingleAdapter<>(R.layout.item_user_tambah_tabungan, (eventType, payload, position) ->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_tambah_tabungan_admin);
            MyUser.getInstance(getContext()).setLastIdNasabah(payload.getUid());
        });
        binding.rv.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe();
    }

    private void observe() {
        mViewModel.listUser.observe(getViewLifecycleOwner(), userModels -> {
            if(userModels != null){
                adapter.setData(userModels);
            }
        });
    }


}
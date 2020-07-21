package com.tdn.qurban.admin.ui.notifications;

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

import com.tdn.domain.model.notifikasiModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.FragmentHomeBinding;
import com.tdn.qurban.databinding.FragmentNotificationsBinding;
import com.tdn.qurban.databinding.FragmentNotificationsBindingImpl;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private AdapterNotifikasiAdmin adapterNotifikasiAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_notifications, container, false);
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        adapterNotifikasiAdmin = new AdapterNotifikasiAdmin(adapterClicked);
        // binding.rv.setAdapter(adapterNotifikasiAdmin);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(notificationsViewModel);
    }

    private void observe(NotificationsViewModel notificationsViewModel) {
        notificationsViewModel.getNotifikasiModelLiveData().observe(getViewLifecycleOwner(), notifikasiModel -> {
            adapterNotifikasiAdmin.setData(notifikasiModel);
        });
    }

    private AdapterClicked adapterClicked = posisi -> {

    };
}
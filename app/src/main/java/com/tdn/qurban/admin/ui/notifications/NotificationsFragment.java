package com.tdn.qurban.admin.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.FragmentNotificationsBinding;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private AdapterNotifikasiAdmin adapterNotifikasiAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_notifications, container, false);
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        adapterNotifikasiAdmin = new AdapterNotifikasiAdmin(getContext());
        binding.rv.setAdapter(adapterNotifikasiAdmin);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(notificationsViewModel);
    }

    private void observe(NotificationsViewModel notificationsViewModel) {
        binding.setIsLoading(false);
        notificationsViewModel.getNotifikasiModelLiveData().observe(getViewLifecycleOwner(), notifikasiModel -> {
            adapterNotifikasiAdmin.setData(notifikasiModel);
        });
    }

    private AdapterClicked adapterClicked = posisi -> {

    };
}
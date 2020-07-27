package com.tdn.qurban.admin.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.tdn.data.Const;
import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.NotifikasiModel;
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.core.AdapterNotifClicked;
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
        adapterNotifikasiAdmin = new AdapterNotifikasiAdmin(getContext(), adapterNotifClicked);
        binding.lyKosong.setVisibility(View.GONE);
        binding.rv.showShimmer();
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
        notificationsViewModel.notifikasiModelLiveData.observe(getViewLifecycleOwner(), notifikasiModel -> {
            if (notifikasiModel != null) {
                binding.lyKosong.setVisibility(View.GONE);
                adapterNotifikasiAdmin.setData(notifikasiModel);
            } else {
                binding.lyKosong.setVisibility(View.VISIBLE);

            }
        });
    }

    private AdapterNotifClicked adapterNotifClicked = (posisi, userModel) -> {
        NotifikasiModel n = adapterNotifikasiAdmin.getFromPosition(posisi);

        if (n.getTipe().equals(Const.TIPE_NOTIF_AJUKAN)) {
            MyUser.getInstance(getContext()).setLastIdNasabah(userModel.getUid());

            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_detail_nasabah);


        } else if (n.getTipe().equals(Const.TIPE_NOTIF_TARIK)) {

            MyUser.getInstance(getContext()).setLastIdNasabah(userModel.getUid());
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_detail_pengajuantarik);

        } else if (n.getTipe().equals(Const.TIPE_NOTIF_AKTIVASI)) {
            MyUser.getInstance(getContext()).setLastIdNasabah(userModel.getUid());
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_detail_nasabah);

        } else if (n.getTipe().equals(Const.TIPE_NOTIF_TAMBAHSALDO)) {
            MyUser.getInstance(getContext()).setLastIdTabungan(n.getId_content());
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_detail_tabungan);


        } else {

        }
    };

}
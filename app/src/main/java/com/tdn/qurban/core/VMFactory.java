package com.tdn.qurban.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tdn.qurban.admin.ui.detailnasabah.DetailNasabahViewModel;
import com.tdn.qurban.admin.ui.home.HomeAdminViewModel;
import com.tdn.qurban.admin.ui.jenishewan.JenisHewaViewModel;
import com.tdn.qurban.admin.ui.jenishewan.TambahHewanViewModel;
import com.tdn.qurban.admin.ui.nasabah.NasabahViewModel;
import com.tdn.qurban.admin.ui.notifications.NotificationsViewModel;
import com.tdn.qurban.admin.ui.tabungan.TabunganAdminViewModel;
import com.tdn.qurban.auth.LoginViewModel;
import com.tdn.qurban.nasabah.ui.aktivasiakun.AktivasiAkunViewModel;
import com.tdn.qurban.nasabah.ui.home.HomeUserViewModel;
import com.tdn.qurban.nasabah.ui.konfirmasipembayaran.KonfirmasiPembayaran;
import com.tdn.qurban.nasabah.ui.konfirmasipembayaran.KonfirmasiPembayaranViewModel;
import com.tdn.qurban.nasabah.ui.notifikasi.NotifikasiNasabahViewModel;

public class VMFactory implements ViewModelProvider.Factory {
    /**
     * variabel
     **/
    private Context context;
    private ActionListener actionListener;
    private AuthListener authListener;
    private String id;

    /**
     * constructor kosong
     */

    public VMFactory() {
    }

    /**
     * constructor
     * Context
     */
    public VMFactory(Context context) {
        this.context = context;
    }

    /**
     * constructor
     * Context
     * ActionListener
     */
    public VMFactory(Context context, ActionListener actionListener) {
        this.context = context;
        this.actionListener = actionListener;
    }

    /**
     * constructor
     * Context
     * AuthListener
     */
    public VMFactory(Context context, AuthListener authListener) {
        this.context = context;
        this.authListener = authListener;
    }

    /**
     * constructor
     * Context
     * AuthListener
     * ActionListener
     */
    public VMFactory(Context context, ActionListener actionListener, AuthListener authListener) {
        this.context = context;
        this.authListener = authListener;
        this.actionListener = actionListener;
    }

    /**
     * constructor
     * Context
     * String
     */
    public VMFactory(Context context, String s) {
        this.context = context;
        this.id = s;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        /*
         * view model factory seluruh layer
         *
         *  TODO:: auth-login
         * */
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(context, authListener);
            /*
              TODO:: admin home
              */
        } else if (modelClass.isAssignableFrom(HomeAdminViewModel.class)) {
            return (T) new HomeAdminViewModel();
            /*
             TODO:: admin detail nasabah
              */
        } else if (modelClass.isAssignableFrom(DetailNasabahViewModel.class)) {
            return (T) new DetailNasabahViewModel(context, id);
            /*
              TODO:: admin jenis hewan
              */
        } else if (modelClass.isAssignableFrom(JenisHewaViewModel.class)) {
            return (T) new JenisHewaViewModel();
            /*
              TODO:: admin tambah hewan
              */
        } else if (modelClass.isAssignableFrom(TambahHewanViewModel.class)) {
            return (T) new TambahHewanViewModel(context, actionListener);
            /*
            TODO:: admin data tabungan
            */
        } else if (modelClass.isAssignableFrom(TabunganAdminViewModel.class)) {
            return (T) new TabunganAdminViewModel();
            // data user admin
        } else if (modelClass.isAssignableFrom(NasabahViewModel.class)) {
            return (T) new NasabahViewModel();
            //notifikasi admin
        } else if (modelClass.isAssignableFrom(NotificationsViewModel.class)) {
            return (T) new NotificationsViewModel();
            //aktifasi user
        } else if (modelClass.isAssignableFrom(AktivasiAkunViewModel.class)) {
            return (T) new AktivasiAkunViewModel(context, actionListener);
            //notifikasi user
        } else if (modelClass.isAssignableFrom(NotifikasiNasabahViewModel.class)) {
            return (T) new NotifikasiNasabahViewModel(context);
            //home user
        } else if (modelClass.isAssignableFrom(HomeUserViewModel.class)) {
            return (T) new HomeUserViewModel();
            //konfirmasi user
        } else if (modelClass.isAssignableFrom(KonfirmasiPembayaranViewModel.class)) {
            return (T) new KonfirmasiPembayaranViewModel(context, actionListener);
            // notifikasi user
        } else if (modelClass.isAssignableFrom(NotifikasiNasabahViewModel.class)) {
            return (T) new NotifikasiNasabahViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}

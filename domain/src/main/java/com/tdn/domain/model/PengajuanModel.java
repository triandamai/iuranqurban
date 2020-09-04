package com.tdn.domain.model;

public class PengajuanModel {
    String id;
    String tipe_pengajuan;
    String user_uid;
    String admin_uid;
    String keterangan;
    String bukti;
    String verifikasi_admin;
    String verifikasi_user;

    public PengajuanModel(String id, String tipe_pengajuan, String user_uid, String admin_uid, String keterangan, String bukti, String verifikasi_admin, String verifikasi_user) {
        this.id = id;
        this.tipe_pengajuan = tipe_pengajuan;
        this.user_uid = user_uid;
        this.admin_uid = admin_uid;
        this.keterangan = keterangan;
        this.bukti = bukti;
        this.verifikasi_admin = verifikasi_admin;
        this.verifikasi_user = verifikasi_user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipe_pengajuan() {
        return tipe_pengajuan;
    }

    public void setTipe_pengajuan(String tipe_pengajuan) {
        this.tipe_pengajuan = tipe_pengajuan;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public String getAdmin_uid() {
        return admin_uid;
    }

    public void setAdmin_uid(String admin_uid) {
        this.admin_uid = admin_uid;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getVerifikasi_admin() {
        return verifikasi_admin;
    }

    public void setVerifikasi_admin(String verifikasi_admin) {
        this.verifikasi_admin = verifikasi_admin;
    }

    public String getVerifikasi_user() {
        return verifikasi_user;
    }

    public void setVerifikasi_user(String verifikasi_user) {
        this.verifikasi_user = verifikasi_user;
    }
}

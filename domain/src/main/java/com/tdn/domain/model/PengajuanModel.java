package com.tdn.domain.model;

public class PengajuanModel {
    String id;
    String tipe_pengajuan;
    String uid;
    String keterangan;
    String bukti;
    String verifikasi_admin;
    String verifikasi_user;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "PengajuanModel{" +
                "id='" + id + '\'' +
                ", tipe_pengajuan='" + tipe_pengajuan + '\'' +
                ", uid='" + uid + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", bukti='" + bukti + '\'' +
                ", verifikasi_admin='" + verifikasi_admin + '\'' +
                ", verifikasi_user='" + verifikasi_user + '\'' +
                '}';
    }
}

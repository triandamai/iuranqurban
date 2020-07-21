package com.tdn.data.entity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class userEntity {
    String uid,
            nama,
            nik,
            no_hp,
            jenis_kelamin,
            alamat,
            nama_ahli_waris,
            hubungan_dengan_ahli_waris,
            level,
            status,
            kartu_identitas,
            hp_wa,
            created_at,
            updated_at;

    public userEntity() {
    }

    public userEntity(String uid, String nama, String nik, String no_hp, String jenis_kelamin, String alamat, String nama_ahli_waris, String hubungan_dengan_ahli_waris, String level, String status, String kartu_identitas, String hp_wa, String created_at, String updated_at) {
        this.uid = uid;
        this.nama = nama;
        this.nik = nik;
        this.no_hp = no_hp;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.nama_ahli_waris = nama_ahli_waris;
        this.hubungan_dengan_ahli_waris = hubungan_dengan_ahli_waris;
        this.level = level;
        this.status = status;
        this.kartu_identitas = kartu_identitas;
        this.hp_wa = hp_wa;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama_ahli_waris() {
        return nama_ahli_waris;
    }

    public void setNama_ahli_waris(String nama_ahli_waris) {
        this.nama_ahli_waris = nama_ahli_waris;
    }

    public String getHubungan_dengan_ahli_waris() {
        return hubungan_dengan_ahli_waris;
    }

    public void setHubungan_dengan_ahli_waris(String hubungan_dengan_ahli_waris) {
        this.hubungan_dengan_ahli_waris = hubungan_dengan_ahli_waris;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKartu_identitas() {
        return kartu_identitas;
    }

    public void setKartu_identitas(String kartu_identitas) {
        this.kartu_identitas = kartu_identitas;
    }

    public String getHp_wa() {
        return hp_wa;
    }

    public void setHp_wa(String hp_wa) {
        this.hp_wa = hp_wa;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "uid='" + uid + '\'' +
                ", nama='" + nama + '\'' +
                ", nik='" + nik + '\'' +
                ", no_hp='" + no_hp + '\'' +
                ", jenis_kelamin='" + jenis_kelamin + '\'' +
                ", alamat='" + alamat + '\'' +
                ", nama_ahli_waris='" + nama_ahli_waris + '\'' +
                ", hubungan_dengan_ahli_waris='" + hubungan_dengan_ahli_waris + '\'' +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                ", kartu_identitas='" + kartu_identitas + '\'' +
                ", hp_wa='" + hp_wa + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}

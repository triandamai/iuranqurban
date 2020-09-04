package com.tdn.domain.model;

public class rencanaModel {
    String uid;
    String jenis_hewan;
    String jenis_hewan_id;
    String jumlah;
    String target_nominal;
    String jenis_pembelian;
    String tempat_penyerahan;
    String created_at;
    String updated_at;

    public rencanaModel() {
    }

    public rencanaModel(String uid, String jenis_hewan, String jenis_hewan_id, String jumlah, String target_nominal, String jenis_pembelian, String tempat_penyerahan, String created_at, String updated_at) {
        this.uid = uid;
        this.jenis_hewan = jenis_hewan;
        this.jenis_hewan_id = jenis_hewan_id;
        this.jumlah = jumlah;
        this.target_nominal = target_nominal;
        this.jenis_pembelian = jenis_pembelian;
        this.tempat_penyerahan = tempat_penyerahan;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getJenis_hewan() {
        return jenis_hewan;
    }

    public void setJenis_hewan(String jenis_hewan) {
        this.jenis_hewan = jenis_hewan;
    }

    public String getJenis_hewan_id() {
        return jenis_hewan_id;
    }

    public void setJenis_hewan_id(String jenis_hewan_id) {
        this.jenis_hewan_id = jenis_hewan_id;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTarget_nominal() {
        return target_nominal;
    }

    public void setTarget_nominal(String target_nominal) {
        this.target_nominal = target_nominal;
    }

    public String getJenis_pembelian() {
        return jenis_pembelian;
    }

    public void setJenis_pembelian(String jenis_pembelian) {
        this.jenis_pembelian = jenis_pembelian;
    }

    public String getTempat_penyerahan() {
        return tempat_penyerahan;
    }

    public void setTempat_penyerahan(String tempat_penyerahan) {
        this.tempat_penyerahan = tempat_penyerahan;
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
}

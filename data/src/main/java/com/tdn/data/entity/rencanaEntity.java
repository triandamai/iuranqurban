package com.tdn.data.entity;

public class rencanaEntity {
    String uid;
	String jenis;
	String jumlah;
	String target_nominal;
	String pembelian;
	String tempat_penyerahan;
	String created_at;
	String updated_at;

    public rencanaEntity() {
    }

    public rencanaEntity(String uid, String jenis, String jumlah, String target_nominal, String pembelian, String tempat_penyerahan, String created_at, String updated_at) {
        this.uid = uid;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.target_nominal = target_nominal;
        this.pembelian = pembelian;
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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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

    public String getPembelian() {
        return pembelian;
    }

    public void setPembelian(String pembelian) {
        this.pembelian = pembelian;
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

    @Override
    public String toString() {
        return "rencanaModel{" +
                "uid='" + uid + '\'' +
                ", jenis='" + jenis + '\'' +
                ", jumlah='" + jumlah + '\'' +
                ", target_nominal='" + target_nominal + '\'' +
                ", pembelian='" + pembelian + '\'' +
                ", tempat_penyerahan='" + tempat_penyerahan + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}

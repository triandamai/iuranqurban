package com.tdn.data.entity;

public class saldoEntity {
    String uid;
    double jumlah;
    String last_updated;

    public saldoEntity() {
    }

    public saldoEntity(String uid, double jumlah, String last_updated) {
        this.uid = uid;
        this.jumlah = jumlah;
        this.last_updated = last_updated;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    @Override
    public String toString() {
        return "saldoModel{" +
                "uid='" + uid + '\'' +
                ", jumlah=" + jumlah +
                ", last_updated='" + last_updated + '\'' +
                '}';
    }
}

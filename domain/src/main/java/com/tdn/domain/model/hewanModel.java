package com.tdn.domain.model;

public class hewanModel {
    String id;
    String jenis;
    String nominal;

    public hewanModel() {
    }

    public hewanModel(String id, String jenis, String nominal) {
        this.id = id;
        this.jenis = jenis;
        this.nominal = nominal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    @Override
    public String toString() {
        return "hewanModel{" +
                "id='" + id + '\'' +
                ", jenis='" + jenis + '\'' +
                ", nominal='" + nominal + '\'' +
                '}';
    }
}

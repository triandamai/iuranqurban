package com.tdn.domain.model;

public class hewanModel {
    String id;
    String jenis;
    String nominal;
    String created_by;

    public hewanModel() {
    }

    public hewanModel(String id, String jenis, String nominal, String created_by) {
        this.id = id;
        this.jenis = jenis;
        this.nominal = nominal;
        this.created_by = created_by;
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

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}

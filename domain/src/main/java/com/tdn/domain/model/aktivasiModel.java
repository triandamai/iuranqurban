package com.tdn.domain.model;

public class aktivasiModel {
    String uid, foto, created_at, updated_at;

    public aktivasiModel() {
    }

    public aktivasiModel(String uid, String foto, String created_at, String updated_at) {
        this.uid = uid;
        this.foto = foto;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
        return "aktivasiModel{" +
                "uid='" + uid + '\'' +
                ", foto='" + foto + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}

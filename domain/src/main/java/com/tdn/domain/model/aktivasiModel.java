package com.tdn.domain.model;

public class aktivasiModel {
    String uid,
            foto,
            activate_by,
            created_at,
            updated_at;

    public aktivasiModel() {
    }

    public aktivasiModel(String uid, String foto, String activate_by, String created_at, String updated_at) {
        this.uid = uid;
        this.foto = foto;
        this.activate_by = activate_by;
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

    public String getActivate_by() {
        return activate_by;
    }

    public void setActivate_by(String activate_by) {
        this.activate_by = activate_by;
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

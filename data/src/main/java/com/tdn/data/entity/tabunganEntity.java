package com.tdn.data.entity;

public class tabunganEntity {
    String id;
    String uid;
    String bukti;
    String nominal;
    String keterangan;
    String status;
    String created_at;
    String updated_at;

    public tabunganEntity() {
    }

    public tabunganEntity(String id, String uid, String bukti, String nominal, String keterangan, String status, String created_at, String updated_at) {
        this.id = id;
        this.uid = uid;
        this.bukti = bukti;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "TabunganModel{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", bukti='" + bukti + '\'' +
                ", nominal='" + nominal + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}

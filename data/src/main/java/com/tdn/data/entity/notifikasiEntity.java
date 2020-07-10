package com.tdn.data.entity;

public class notifikasiEntity {
    String id;
    String from_uid;
    String broad_to;
    String body;
    String tipe;
    String created_at;

    public notifikasiEntity() {
    }

    public notifikasiEntity(String id, String from_uid, String broad_to, String body, String tipe, String created_at) {
        this.id = id;
        this.from_uid = from_uid;
        this.broad_to = broad_to;
        this.body = body;
        this.tipe = tipe;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(String from_uid) {
        this.from_uid = from_uid;
    }

    public String getBroad_to() {
        return broad_to;
    }

    public void setBroad_to(String broad_to) {
        this.broad_to = broad_to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "notifikasiModel{" +
                "id='" + id + '\'' +
                ", from_uid='" + from_uid + '\'' +
                ", broad_to='" + broad_to + '\'' +
                ", body='" + body + '\'' +
                ", tipe='" + tipe + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}

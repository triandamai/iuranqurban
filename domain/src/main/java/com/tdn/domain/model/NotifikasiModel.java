package com.tdn.domain.model;

public class NotifikasiModel {
    String id;
    String from_uid;
    String broad_to;
    String body;
    String tipe;
    String id_content;
    String status;
    String created_at;

    public NotifikasiModel() {
    }

    public NotifikasiModel(String id, String from_uid, String broad_to, String body, String tipe, String id_content, String status, String created_at) {
        this.id = id;
        this.from_uid = from_uid;
        this.broad_to = broad_to;
        this.body = body;
        this.tipe = tipe;
        this.id_content = id_content;
        this.status = status;
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

    public String getId_content() {
        return id_content;
    }

    public void setId_content(String id_content) {
        this.id_content = id_content;
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

    @Override
    public String toString() {
        return "NotifikasiModel{" +
                "id='" + id + '\'' +
                ", from_uid='" + from_uid + '\'' +
                ", broad_to='" + broad_to + '\'' +
                ", body='" + body + '\'' +
                ", tipe='" + tipe + '\'' +
                ", id_content='" + id_content + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}

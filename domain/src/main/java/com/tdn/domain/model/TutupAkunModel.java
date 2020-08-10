package com.tdn.domain.model;

public class TutupAkunModel {
    String id;
    String uid;
    String title;
    String desc;
    String user_acc;
    String admin_acc;
    double created_at;
    double updated_at;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUser_acc() {
        return user_acc;
    }

    public void setUser_acc(String user_acc) {
        this.user_acc = user_acc;
    }

    public String getAdmin_acc() {
        return admin_acc;
    }

    public void setAdmin_acc(String admin_acc) {
        this.admin_acc = admin_acc;
    }

    public double getCreated_at() {
        return created_at;
    }

    public void setCreated_at(double created_at) {
        this.created_at = created_at;
    }

    public double getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(double updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "TutupAkunModel{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", user_acc='" + user_acc + '\'' +
                ", admin_acc='" + admin_acc + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}

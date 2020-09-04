package com.tdn.domain.model;

public class TutupAkunModel {
    String id;
    String user_uid;
    String admin_uid;
    String title;
    String desc;
    String user_acc;
    String admin_acc;
    double created_at;
    double updated_at;

    public TutupAkunModel() {
    }

    public TutupAkunModel(String id, String user_uid, String admin_uid, String title, String desc, String user_acc, String admin_acc, double created_at, double updated_at) {
        this.id = id;
        this.user_uid = user_uid;
        this.admin_uid = admin_uid;
        this.title = title;
        this.desc = desc;
        this.user_acc = user_acc;
        this.admin_acc = admin_acc;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public String getAdmin_uid() {
        return admin_uid;
    }

    public void setAdmin_uid(String admin_uid) {
        this.admin_uid = admin_uid;
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
}

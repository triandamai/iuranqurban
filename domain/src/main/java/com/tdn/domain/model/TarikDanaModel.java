package com.tdn.domain.model;

public class TarikDanaModel {

    String id;
    String user_uid;
    String admin_uid;
    String title;
    String desc;
    double nominal;
    String norek;
    String atas_nama;
    String admin_acc;
    String user_acc;
    double created_at;
    double updated_at;

    public TarikDanaModel() {
    }

    public TarikDanaModel(String id, String user_uid, String admin_uid, String title, String desc, double nominal, String norek, String atas_nama, String admin_acc, String user_acc, double created_at, double updated_at) {
        this.id = id;
        this.user_uid = user_uid;
        this.admin_uid = admin_uid;
        this.title = title;
        this.desc = desc;
        this.nominal = nominal;
        this.norek = norek;
        this.atas_nama = atas_nama;
        this.admin_acc = admin_acc;
        this.user_acc = user_acc;
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

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getAtas_nama() {
        return atas_nama;
    }

    public void setAtas_nama(String atas_nama) {
        this.atas_nama = atas_nama;
    }

    public String getAdmin_acc() {
        return admin_acc;
    }

    public void setAdmin_acc(String admin_acc) {
        this.admin_acc = admin_acc;
    }

    public String getUser_acc() {
        return user_acc;
    }

    public void setUser_acc(String user_acc) {
        this.user_acc = user_acc;
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

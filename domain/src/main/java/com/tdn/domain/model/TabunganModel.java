package com.tdn.domain.model;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class TabunganModel {
    String id;
    String user_uid;
    String admin_uid;
    String bukti;
    String nominal;
    String keterangan;
    String status;
    String created_at;
    String updated_at;

    public TabunganModel() {
    }

    public TabunganModel(String id, String user_uid, String admin_uid, String bukti, String nominal, String keterangan, String status, String created_at, String updated_at) {
        this.id = id;
        this.user_uid = user_uid;
        this.admin_uid = admin_uid;
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

    public String created_at_to_date() {
        Date d = new Date(created_at);
        return String.valueOf(d);
    }
}

package com.tdn.data.pref;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.tdn.data.Const;


public class MyUser {
    @SuppressLint("StaticFieldLeak")
    private static MyUser myUser;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private Context context;

    @SuppressLint("CommitPrefEdits")
    private MyUser(Context ctx) {
        context = ctx;
        sharedPreferences = ctx.getSharedPreferences(Const.KEY_PREF, 0);
        editor = sharedPreferences.edit();
    }

    public static MyUser getInstance(Context context) {
        if (myUser == null)
            myUser = new MyUser(context);

        return myUser;
    }


    public void setLastIdNasabah(String val) {
        editor.putString(Const.KEY_PREF_LAST_NASABAH_ID, val);
        editor.apply();
    }


    public String getLastIdNasabah() {
        return sharedPreferences.getString(Const.KEY_PREF_LAST_NASABAH_ID, Const.VALUE_PREF_NULL);
    }

    public void setLastIdNotif(String val) {
        editor.putString(Const.KEY_PREF_LAST_NOTIF_ID, val);
        editor.apply();
    }


    public String getLastIdNotif() {
        return sharedPreferences.getString(Const.KEY_PREF_LAST_NOTIF_ID, Const.VALUE_PREF_NULL);
    }

    public void setLastIdTabungan(String val) {
        editor.putString(Const.KEY_PREF_LAST_TABUNGAN_ID, val);
        editor.apply();
    }


    public String getLastIdTabungan() {
        return sharedPreferences.getString(Const.KEY_PREF_LAST_TABUNGAN_ID, Const.VALUE_PREF_NULL);
    }
}

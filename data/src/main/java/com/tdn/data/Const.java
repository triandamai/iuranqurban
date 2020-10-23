package com.tdn.data;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Const {
    public static final String BASE_CHILD = "IuranQurban";
    public static final String CHILD_USER = "USER";
    public static final String CHILD_SALDO = "SALDO";
    public static final String CHILD_RENCANA = "RENCANA";
    public static final String CHILD_TABUNGAN = "TABUNGAN";
    public static final String CHILD_NOTIF_USER = "NOTIFIKASI_USER";
    public static final String CHILD_NOTIF_ADMIN = "NOTIFIKASI_ADMIN";
    public static final String CHILD_HEWAN = "HEWAN";
    public static final String CHILD_AKTIVASI = "AKTIVASI";
    public static final String BUCKET_AKTIVASI = "AKTIVASI";
    public static final String BUCKET_TABUNGAN = "TABUNGAN";
    public static final String CHILD_USER_REGISTRASI = "registrasi";
    public static final String CHILD_USER_RENCANA = "rencana";
    public static final String CHILD_USER_LEVEL = "level";
    public static final String CHILD_ORDERBYTIPE = "tipe_pengajuan";
    public static final String CHILD_ORDERBYTIME = "created_at";
    public static final String CHILD_ORDERBYSTATUS = "status";
    public static final String CHILD_ORDERBYUID = "uid";
    public static final String CHILD_TARIKDANA = "TARIKDANA";
    public static final String CHILD_TUTUPAKUN = "TUTUPAKUN";

    public static final String SUDAH_REGISTRASI = "YES";
    public static final String BELUM_REGISTRASI = "NO";
    public static final String SUDAH_RENCANA = "YES";
    public static final String BELUM_RENCANA = "NO";


    public static final String USER_ADA = "ada";
    public static final String USER_TIDAK_ADA = "tidak_ada";
    public static final String USER_LEVEL_ADMIN = "ADMIN";
    public static final String USER_LEVEL_PANITIA = "PANITIA";
    public static final String USER_LEVEL_NASABAH = "NASABAH";

    public static final String STATUS_USER_AKTIF = "AKTIF";
    public static final String STATUS_USER_NONAKTIF = "NONAKTIF";
    public static final String STATUS_USER_PENDING = "PENDING";


    public static final String TIPE_NOTIF_TAMBAHSALDO = "TAMBAHSALDO";

    public static final String STATUS_NOTIF_TAMBAHSALDO_DITOLAK = "TAMBAHSALDOTOLAK";
    public static final String STATUS_NOTIF_TAMBAHSALDO_DITERIMA = "TAMBAHSALDOTERIMA";
    public static final String STATUS_NOTIF_TAMBAHSALDO_MENUNGGU = "PENDING";
    public static final String STATUS_NOTIF_TAMBAHSALDO_DITARIK = "SUDAHDIAMBIL";


    public static final String TIPE_NOTIF_AKTIVASI = "AKTIVASI";
    public static final String STATUS_NOTIF_AKTIVASI_DITOLAK = "AKTIVASITOLAK";
    public static final String STATUS_NOTIF_AKTIVASI_DITERIMA = "AKTIVASIACC";
    public static final String STATUS_NOTIF_AKTIVASI_MENUNGGU = "PENDING";

    public static final String PENGAJUAN_VERIFIKASI_YES = "YES";
    public static final String PENGAJUAN_VERIFIKASI_NO = "NO";

    public static final String TIPE_NOTIF_TARIK = "TARIKDANA";
    public static final String STATUS_NOTIF_PENGAJUANTARIKDANA_DITERIMA = "DITERIMA";
    public static final String STATUS_NOTIF_PENGAJUANTARIKDANA_DITOLAK = "DITOLAK";
    public static final String STATUS_NOTIF_PENGAJUANTARIKDANA_MENUNGGU = "PENDING";

    public static final String TIPE_NOTIF_TUTUP = "TUTUPAKUN";
    public static final String STATUS_NOTIF_TUTUP_DITERIMA = "DITERIMA";
    public static final String STATUS_NOTIF_TUTUP_DITOLAK = "DITOLAK";
    public static final String STATUS_NOTIF_TUTUP_MENUNGGU = "PENDING";

    public static final String FILE_KOSONG = "kosong";

    public static final String JK_PR = "PEREMPUAN";
    public static final String JK_LK = "LAKI-LAKI";

    public static final String[] hubungan = {
            Const.HUBUNGAN_ANAK,
            Const.HUBUNGAN_SAUDARA,
            Const.HUBUNGAN_ORANGTUA,
            Const.HUBUNGAN_SUAMI,
            Const.HUBUNGAN_ISTRI,
            Const.HUBUNGAN_LAINNYA};

    public static final String[] jeniskelamin = {
            Const.JK_LK,
            Const.JK_PR};

    public static final String[] tempat = {"Masjid Baitul Muhsinin", "Lainnya"};
    public static final String[] jenisbeli = {"Beli Sendiri", "Melalui Panitia"};

    public static final String HUBUNGAN_SAUDARA = "SAUDARA";
    public static final String HUBUNGAN_ANAK = "ANAK";
    public static final String HUBUNGAN_ORANGTUA = "ORANG TUA";
    public static final String HUBUNGAN_SUAMI = "SUAMI";
    public static final String HUBUNGAN_ISTRI = "ISTRI";
    public static final String HUBUNGAN_LAINNYA = "LAINNYA";

    public static final String KEY_PREF_LAST_NASABAH_ID = "nsbh";
    public static final String KEY_PREF_LAST_NOTIF_ID = "nsbha";
    public static final String KEY_PREF_LAST_TABUNGAN_ID = "nsbhb";
    public static final String VALUE_PREF_NULL = "NULL";
    public static final String KEY_PREF = "data_iuran_qurban";
    public static final String KEY_PREF_LAST_HEWAN_ID = "idhewan";
    public static final String KEY_PREF_AKTIF = "status";
    public static final String CHILD_ORDERBYCREATEDAT = "created_at";
    public static final String CHILD_JENIS_HEWAN_RENCANA = "jenis";


    public static int REQ_IMAGE = 100;
    public static int REQ_SIGN = 200;

    @SuppressLint("DefaultLocale")
    public static String currency(Double d) {

        return "Rp " + String.format("%.2f", d);//formatter.format("Rp " + d);
    }

    @SuppressLint("DefaultLocale")
    public static String currency(String val) {
        Double d = Double.parseDouble(val);
        return "Rp " + String.format("%.2f", d);
    }

    public static String date_at(Long val) {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(val);

        return String.valueOf(dateText);
    }

    public static String date_at(String val) {
        long result = Long.parseLong(val);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateText = df2.format(result);
        return String.valueOf(dateText);
    }

    public static String date_at(long val) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateText = df2.format(val);

        return String.valueOf(dateText);
    }
}

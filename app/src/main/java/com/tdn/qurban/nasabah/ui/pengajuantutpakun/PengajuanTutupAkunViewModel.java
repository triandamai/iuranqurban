package com.tdn.qurban.nasabah.ui.pengajuantutpakun;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdn.data.Const;
import com.tdn.domain.model.NotifikasiModel;
import com.tdn.domain.model.TutupAkunModel;
import com.tdn.qurban.core.ActionListener;

import java.util.Date;

public class PengajuanTutupAkunViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ActionListener actionListener;
    private Context context;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public PengajuanTutupAkunViewModel(Context context, ActionListener actionListener) {
        this.actionListener = actionListener;
        this.context = context;
    }

    public void ajukan() {
        actionListener.onStart();
        String idcontent = databaseReference.push().getKey();
        TutupAkunModel model = new TutupAkunModel();
        model.setAdmin_acc(Const.PENGAJUAN_VERIFIKASI_NO);
        model.setCreated_at(new Date().getTime());
        model.setDesc("Tutup Akun");
        model.setTitle("Tutup Akun");
        model.setUser_uid(firebaseAuth.getCurrentUser().getUid());
        model.setUpdated_at(new Date().getTime());
        model.setUser_acc(Const.PENGAJUAN_VERIFIKASI_YES);
        model.setAdmin_uid("kosong");

        NotifikasiModel notifikasiModel = new NotifikasiModel();
        notifikasiModel.setId_content(idcontent);
        notifikasiModel.setCreated_at(String.valueOf(new Date().getTime()));
        notifikasiModel.setTo_uid(Const.USER_LEVEL_PANITIA);
        notifikasiModel.setFrom_uid(firebaseAuth.getCurrentUser().getUid());
        notifikasiModel.setTipe(Const.TIPE_NOTIF_TUTUP);
        notifikasiModel.setStatus(Const.STATUS_NOTIF_PENGAJUANTARIKDANA_MENUNGGU);
        notifikasiModel.setBody("Meminta menutup akun");
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_TUTUPAKUN)
                .child(idcontent)
                .setValue(model).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                databaseReference.child(Const.BASE_CHILD)
                        .child(Const.CHILD_NOTIF_ADMIN)
                        .push()
                        .setValue(notifikasiModel).addOnCompleteListener(task1 -> actionListener.onSuccess("Berhasil"));
            } else {
                actionListener.onError("Gagal melakukan pengajuan");
            }
        }).addOnFailureListener(e -> {
            actionListener.onError("Gagal melakukan pengajuan");
        });

    }
}
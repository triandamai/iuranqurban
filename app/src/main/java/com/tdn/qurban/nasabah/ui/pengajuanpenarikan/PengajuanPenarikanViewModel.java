package com.tdn.qurban.nasabah.ui.pengajuanpenarikan;

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
import com.tdn.domain.model.TarikDanaModel;
import com.tdn.qurban.core.ActionListener;

import java.util.Date;

public class PengajuanPenarikanViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ActionListener actionListener;
    private Context context;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public PengajuanPenarikanViewModel(Context context, ActionListener actionListener) {
        this.actionListener = actionListener;
        this.context = context;
    }

    public void simpan(TarikDanaModel tarikDanaModel) {
        actionListener.onStart();
        NotifikasiModel notifikasiModel = new NotifikasiModel();
        String idcontent = databaseReference.push().getKey();
        notifikasiModel.setId_content(idcontent);
        notifikasiModel.setCreated_at(String.valueOf(new Date().getTime()));
        notifikasiModel.setTo_uid(Const.USER_LEVEL_PANITIA);
        notifikasiModel.setFrom_uid(firebaseAuth.getCurrentUser().getUid());
        notifikasiModel.setTipe(Const.TIPE_NOTIF_TARIK);
        notifikasiModel.setStatus(Const.STATUS_NOTIF_PENGAJUANTARIKDANA_MENUNGGU);
        notifikasiModel.setBody(String.valueOf(tarikDanaModel.getNominal()));

        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_TARIKDANA)
                .child(idcontent)
                .setValue(tarikDanaModel)
                .addOnCompleteListener(task -> {
                    databaseReference.child(Const.BASE_CHILD)
                            .child(Const.CHILD_NOTIF_ADMIN)
                            .push()
                            .setValue(notifikasiModel);
                    actionListener.onSuccess("Berhasil");
                }).addOnFailureListener(e -> {
            actionListener.onError(e.getMessage());
        });
    }
}
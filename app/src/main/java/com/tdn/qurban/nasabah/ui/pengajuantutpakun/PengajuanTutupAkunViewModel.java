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

public class PengajuanTutupAkunViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ActionListener actionListener;

    public PengajuanTutupAkunViewModel(Context context, ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void ajukan() {
        actionListener.onStart();
        TutupAkunModel model = new TutupAkunModel();
        NotifikasiModel notifikasiModel = new NotifikasiModel();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(firebaseAuth.getCurrentUser().getUid())
                .setValue(model).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                databaseReference.child(Const.BASE_CHILD)
                        .child(Const.CHILD_NOTIF_ADMIN)
                        .push()
                        .setValue(notifikasiModel).addOnCompleteListener(task1 -> actionListener.onSuccess(""));
            } else {
                actionListener.onError("Gagal melakukan pengajuan");
            }
        }).addOnFailureListener(e -> {

        });

    }
}
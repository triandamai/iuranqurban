package com.tdn.qurban.nasabah.ui.aktivasiakun;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.tdn.data.Const;
import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.NotifikasiModel;
import com.tdn.domain.model.aktivasiModel;
import com.tdn.qurban.core.ActionListener;

import java.io.File;
import java.util.Date;
import java.util.Objects;

public class AktivasiAkunViewModel extends ViewModel {
    private Context context;
    private ActionListener actionListener;
    public MutableLiveData<String> foto = new MutableLiveData<>();
    public MutableLiveData<String> progress = new MutableLiveData<>();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private StorageReference bucket = FirebaseStorage.getInstance().getReference();

    public AktivasiAkunViewModel(Context context, ActionListener listener) {
        this.actionListener = listener;
        this.foto.setValue(null);
        this.progress.setValue("");
    }

    public MutableLiveData<String> getFoto() {
        if (foto.getValue() == null) {
            foto = new MutableLiveData<>();
        }
        return foto;
    }

    public MutableLiveData<String> getProgress() {
        if (progress.getValue() == null) {
            progress = new MutableLiveData<>();
        }
        return progress;
    }

    public void simpan() {
        actionListener.onStart();

        if (foto.getValue() != null) {
            Uri file = Uri.fromFile(new File(foto.getValue()));
            final StorageReference storage = bucket
                    .child(Const.CHILD_AKTIVASI + "/" + firebaseAuth.getCurrentUser().getUid() + ".jpeg");

            StorageTask<UploadTask.TaskSnapshot> uploadTask = storage.putFile(file);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return storage.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        aktivasiModel m = new aktivasiModel();
                        m.setFoto(downloadUri.toString());
                        m.setUid(firebaseAuth.getCurrentUser().getUid());
                        m.setUpdated_at(String.valueOf(new Date().getTime()));
                        m.setCreated_at(String.valueOf(new Date().getTime()));

                        databaseReference.child(Const.CHILD_AKTIVASI)
                                .child(firebaseAuth.getCurrentUser().getUid())
                                .setValue(m);

                        NotifikasiModel n = new NotifikasiModel();
                        n.setBody(Const.STATUS_NOTIF_AKTIVASI_MENUNGGU);
                        n.setTipe(Const.TIPE_NOTIF_AKTIVASI);
                        n.setFrom_uid(firebaseAuth.getCurrentUser().getUid());
                        n.setTo_uid(Const.USER_LEVEL_PANITIA);
                        n.setCreated_at(String.valueOf(new Date().getTime()));

                        databaseReference.child(Const.CHILD_NOTIF_ADMIN)
                                .push()
                                .setValue(n);

                        actionListener.onSuccess("Berhasil : ");

                    } else {
                        actionListener.onError("Gagal : task gagal");
                    }
                }
            });


        } else {
            actionListener.onError("Isi semua data");
        }
    }
}
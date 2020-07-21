package com.tdn.qurban.nasabah.ui.aktivasiakun;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tdn.data.Const;
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
    private StorageReference bucket = FirebaseStorage.getInstance().getReference().child(Const.BUCKET_AKTIVASI);

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
            UploadTask uploadTask = bucket.child(firebaseAuth.getCurrentUser().getUid()).putFile(file);
            uploadTask.continueWithTask(task1 -> {
                if (!task1.isSuccessful()) {
                    throw Objects.requireNonNull(task1.getException());
                }

                return bucket.getDownloadUrl();
            }).addOnSuccessListener(uri -> {
                actionListener.onSuccess("Berhasil : ");
            }).addOnCompleteListener(task12 -> {
                if (task12.isComplete()) {
                    Uri downloadUri = task12.getResult();
                    aktivasiModel m = new aktivasiModel();
                    m.setFoto(task12.getResult().toString());
                    m.setUid(firebaseAuth.getCurrentUser().getUid());
                    m.setUpdated_at(String.valueOf(new Date().getTime()));
                    m.setCreated_at(String.valueOf(new Date().getTime()));
                    databaseReference.child(Const.CHILD_AKTIVASI)
                            .child(firebaseAuth.getCurrentUser().getUid())
                            .setValue(m)
                            .addOnFailureListener(e -> {
                                actionListener.onError("Gagal : " + e.getLocalizedMessage());
                            })
                            .addOnSuccessListener(aVoid -> {
                                actionListener.onSuccess("Berhasil : ");
                            });
                } else {
                    actionListener.onError("Gagal upload");
                }
            });

        } else {
            actionListener.onError("Isi semua data");
        }
    }
}
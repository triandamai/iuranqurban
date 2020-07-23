package com.tdn.qurban.nasabah.ui.konfirmasipembayaran;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.tdn.data.Const;
import com.tdn.domain.model.TabunganModel;
import com.tdn.qurban.core.ActionListener;

import java.io.File;
import java.util.Date;

public class KonfirmasiPembayaranViewModel extends ViewModel {
    private Context context;
    private ActionListener listener;
    public MutableLiveData<String> nominal = new MutableLiveData<>();
    public MutableLiveData<String> ket = new MutableLiveData<>();
    public MutableLiveData<String> foto = new MutableLiveData<>();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private StorageReference bucket = FirebaseStorage.getInstance().getReference();

    public KonfirmasiPembayaranViewModel(Context context, ActionListener listener) {
        this.context = context;
        this.listener = listener;
        this.nominal.setValue("");
        this.ket.setValue("");
    }

    public void simpan() {
        listener.onStart();
        if (foto.getValue() != null) {
            Uri file = Uri.fromFile(new File(foto.getValue()));
            final StorageReference storage = bucket
                    .child(Const.CHILD_TABUNGAN + "/" + new Date().getTime() + ".jpeg");

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
                        TabunganModel m = new TabunganModel();
                        m.setBukti(downloadUri.toString());
                        m.setCreated_at(String.valueOf(new Date().getTime()));
                        m.setUpdated_at(String.valueOf(new Date().getTime()));
                        m.setUid(firebaseAuth.getCurrentUser().getUid());
                        m.setKeterangan(ket.getValue());
                        m.setNominal(nominal.getValue());
                        m.setStatus(Const.STATUS_NOTIF_TAMBAHSALDO_PENDING);
                        databaseReference.child(Const.CHILD_TABUNGAN)
                                .child(firebaseAuth.getCurrentUser().getUid())
                                .push()
                                .setValue(m);

                        listener.onSuccess("Berhasil : ");
                    } else {
                        listener.onError("Gagal : task gagal");
                    }
                }
            });


        } else {
            listener.onError("Isi semua data");
        }
    }
}
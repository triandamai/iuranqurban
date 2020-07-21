package com.tdn.qurban.admin.ui.jenishewan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdn.data.Const;
import com.tdn.domain.model.hewanModel;
import com.tdn.qurban.core.ActionListener;

public class TambahHewanViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<String> namaHewan;
    public MutableLiveData<String> nomminalHewan;
    private ActionListener listener;
    private Context context;

    public TambahHewanViewModel(Context context, ActionListener listener) {
        this.listener = listener;
        this.context = context;
        this.namaHewan = new MutableLiveData<>();
        this.nomminalHewan = new MutableLiveData<>();

    }

    public void simpan() {
        listener.onStart();
        String key = databaseReference.push().getKey();
        hewanModel m = new hewanModel();
        m.setId(key);
        m.setNominal(nomminalHewan.getValue());
        m.setJenis(namaHewan.getValue());
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_HEWAN).child(key).setValue(m)
                .addOnSuccessListener(aVoid -> {
                    listener.onSuccess("Sukses ");
                }).addOnCompleteListener(task -> {
            listener.onSuccess("Sukses ");
        }).addOnFailureListener(e -> {
            listener.onError("Gagal : " + e.getLocalizedMessage());
        });
    }
}
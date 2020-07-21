package com.tdn.qurban.nasabah.ui.notifikasi;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.NotifikasiModel;

import java.util.ArrayList;
import java.util.List;

public class NotifikasiNasabahViewModel extends ViewModel {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private Context context;
    private LiveData<List<NotifikasiModel>> notifikasiModelLiveData = new MutableLiveData<>();
    private ValueEventListener notif;

    public NotifikasiNasabahViewModel(Context context) {
        this.context = context;
        getMyNotifikasi();
    }

    public LiveData<List<NotifikasiModel>> getNotifikasiModelLiveData() {
        if (notifikasiModelLiveData == null) {
            notifikasiModelLiveData.getValue().addAll(null);
        }
        return notifikasiModelLiveData;
    }

    private void getMyNotifikasi() {

        notif = databaseReference.child(Const.CHILD_NOTIF_USER).child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<NotifikasiModel> NotifikasiModels = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {
                                NotifikasiModel n = data.getValue(NotifikasiModel.class);
                                n.setId(data.getKey());
                                NotifikasiModels.add(n);
                            }
                            notifikasiModelLiveData.getValue().addAll(NotifikasiModels);

                        } else {
                            notifikasiModelLiveData.getValue().addAll(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(notif);
    }
}
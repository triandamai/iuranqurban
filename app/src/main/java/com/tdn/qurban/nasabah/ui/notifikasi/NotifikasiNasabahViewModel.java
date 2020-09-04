package com.tdn.qurban.nasabah.ui.notifikasi;

import android.content.Context;
import android.util.Log;

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
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Context context;
    public LiveData<List<NotifikasiModel>> notifikasiModelLiveData;


    public NotifikasiNasabahViewModel() {
        notifikasiModelLiveData = getNotifikasiModelLiveData();
    }

    public LiveData<List<NotifikasiModel>> getNotifikasiModelLiveData() {
        final MutableLiveData<List<NotifikasiModel>> notif = new MutableLiveData<>();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_NOTIF_USER)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            List<NotifikasiModel> NotifikasiModels = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {
                                NotifikasiModel n = data.getValue(NotifikasiModel.class);
                                assert n != null;
                                n.setId(data.getKey());
                                Log.e("notif user", data.toString());
                                NotifikasiModels.add(n);
                            }
                            notif.setValue(NotifikasiModels);

                        } else {
                            notif.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        notif.setValue(null);
                    }
                });
        return notif;
    }


}
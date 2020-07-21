package com.tdn.qurban.admin.ui.notifications;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.notifikasiModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    private LiveData<List<notifikasiModel>> notifikasiModelLiveData;

    public NotificationsViewModel() {
        notifikasiModelLiveData = new MutableLiveData<>();
        getAllNotifikasi();
    }

    private void getAllNotifikasi() {
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_NOTIF_ADMIN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            for (DataSnapshot data : snapshot.getChildren()) {
                                notifikasiModel notifikasiModel = data.getValue(notifikasiModel.class);
                                notifikasiModel.setId(data.getKey());

                                notifikasiModelLiveData.getValue().add(notifikasiModel);
                            }

                        } else {
                            notifikasiModelLiveData = new MutableLiveData<>();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        notifikasiModelLiveData = new MutableLiveData<>();
                    }
                });

    }

    public LiveData<List<notifikasiModel>> getNotifikasiModelLiveData() {
        if (notifikasiModelLiveData == null) {
            notifikasiModelLiveData = new MutableLiveData<>();
        }
        return notifikasiModelLiveData;
    }
}
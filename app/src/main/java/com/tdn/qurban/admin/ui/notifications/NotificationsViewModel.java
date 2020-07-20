package com.tdn.qurban.admin.ui.notifications;

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

public class NotificationsViewModel extends ViewModel {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);

    private ValueEventListener notifikasiListener;
    private LiveData<notifikasiModel> notifikasiModelLiveData;

    public NotificationsViewModel() {
        getAllNotifikasi();
    }

    private void getAllNotifikasi() {
        notifikasiListener = databaseReference.child(Const.CHILD_NOTIF_ADMIN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        databaseReference.addValueEventListener(notifikasiListener);
    }

    public LiveData<notifikasiModel> getNotifikasiModelLiveData() {
        if (notifikasiModelLiveData == null) {
            notifikasiModelLiveData = new MutableLiveData<>();
        }
        return notifikasiModelLiveData;
    }
}
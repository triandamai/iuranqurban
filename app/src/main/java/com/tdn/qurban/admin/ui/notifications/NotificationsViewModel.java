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
import com.tdn.domain.model.NotifikasiModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    private LiveData<List<NotifikasiModel>> notifikasiModelLiveData;

    public NotificationsViewModel() {
        notifikasiModelLiveData = getAllNotifikasi();

    }

    private LiveData<List<NotifikasiModel>> getAllNotifikasi() {
        final MutableLiveData<List<NotifikasiModel>> notif = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_NOTIF_ADMIN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<NotifikasiModel> list = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {
                                NotifikasiModel notifikasiModel = data.getValue(NotifikasiModel.class);
                                assert notifikasiModel != null;
                                notifikasiModel.setId(data.getKey());
                                list.add(notifikasiModel);
                            }
                            notif.setValue(list);

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
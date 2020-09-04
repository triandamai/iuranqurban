package com.tdn.qurban.admin.ui.pengajuan;

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
import com.tdn.domain.model.TutupAkunModel;
import com.tdn.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class PenutupanAkunViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);

    public LiveData<List<TutupAkunModel>> tutupAkunLiveData;

    public PenutupanAkunViewModel() {
        this.tutupAkunLiveData = getTutupAkunModel();
    }


    public LiveData<List<TutupAkunModel>> getTutupAkunModel() {
        final MutableLiveData<List<TutupAkunModel>> listMutableLiveData = new MutableLiveData<>();
        databaseReference.child(Const.CHILD_TUTUPAKUN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<TutupAkunModel> userModelList = new ArrayList<>();
                    for (DataSnapshot data : snapshot.getChildren()) {
                        TutupAkunModel userModel = data.getValue(TutupAkunModel.class);
                        userModel.setUser_uid(data.getKey());
                        userModelList.add(userModel);
                    }
                    listMutableLiveData.setValue(userModelList);
                } else {
                    listMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listMutableLiveData.setValue(null);
            }
        });
        return listMutableLiveData;
    }


}
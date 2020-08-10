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
import com.tdn.domain.model.TarikDanaModel;

import java.util.ArrayList;
import java.util.List;

public class PenarikanViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);

    public LiveData<List<TarikDanaModel>> tutupakunlivedata;

    public PenarikanViewModel() {
        this.tutupakunlivedata = getTarikDana();
    }


    public LiveData<List<TarikDanaModel>> getTarikDana() {
        final MutableLiveData<List<TarikDanaModel>> listMutableLiveData = new MutableLiveData<>();
        databaseReference.child(Const.CHILD_USER)
                .orderByChild(Const.CHILD_TARIKDANA).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<TarikDanaModel> userModelList = new ArrayList<>();
                    for (DataSnapshot data : snapshot.getChildren()) {
                        TarikDanaModel userModel = data.getValue(TarikDanaModel.class);
                        assert userModel != null;
                        userModel.setId(data.getKey());
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
package com.tdn.qurban.admin.ui.jenishewan;

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
import com.tdn.domain.model.hewanModel;

import java.util.ArrayList;
import java.util.List;

public class JenisHewaViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Const.BASE_CHILD);
    private ValueEventListener jenishewanListener;
    private LiveData<List<hewanModel>> jenisHewan;

    public JenisHewaViewModel() {

        getJenisHewan();
    }

    private void getJenisHewan() {
        jenishewanListener = databaseReference.child(Const.CHILD_HEWAN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<hewanModel> hewanModelList = new ArrayList<>();
                    for (DataSnapshot data : snapshot.getChildren()) {
                        hewanModel hewanModel = data.getValue(hewanModel.class);
                        hewanModel.setId(data.getKey());
                        hewanModelList.add(hewanModel);
                    }
                    jenisHewan.getValue().addAll(hewanModelList);
                } else {
                    jenisHewan = new MutableLiveData<>();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                jenisHewan = new MutableLiveData<>();
            }
        });
        databaseReference.addValueEventListener(jenishewanListener);

    }

    public LiveData<List<hewanModel>> getJenisHewaViewModel() {
        if (jenisHewan == null) {
            jenisHewan = new MutableLiveData<>();
        }
        return jenisHewan;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(jenishewanListener);
    }
}
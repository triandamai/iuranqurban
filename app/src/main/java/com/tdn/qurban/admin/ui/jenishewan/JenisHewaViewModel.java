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
import com.tdn.domain.model.HewanModel;

import java.util.ArrayList;
import java.util.List;

public class JenisHewaViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    public JenisHewaViewModel() {


    }

    public LiveData<List<HewanModel>> getJenisHewan() {
        final MutableLiveData<List<HewanModel>> jenisHewan = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_HEWAN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<HewanModel> hewanModelList = new ArrayList<>();
                    for (DataSnapshot data : snapshot.getChildren()) {

                        HewanModel hewanModel = data.getValue(HewanModel.class);
                        hewanModel.setId(data.getKey());

                        hewanModelList.add(hewanModel);


                    }
                    jenisHewan.setValue(hewanModelList);

                } else {
                    jenisHewan.setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                jenisHewan.setValue(null);
            }
        });
        return jenisHewan;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
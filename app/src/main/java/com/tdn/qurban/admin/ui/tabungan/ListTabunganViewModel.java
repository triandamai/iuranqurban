package com.tdn.qurban.admin.ui.tabungan;

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
import com.tdn.domain.model.HewanModel;

import java.util.ArrayList;
import java.util.List;

public class ListTabunganViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public LiveData<List<HewanModel>> hewanModelList;

    public ListTabunganViewModel() {
        getListtab("");
        hewanModelList = getListhewan();
    }

    private LiveData<List<HewanModel>> getListhewan() {
        MutableLiveData<List<HewanModel>> data = new MutableLiveData<>();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_HEWAN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<HewanModel> hewanModels = new ArrayList<>();
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                HewanModel hewan = dataSnapshot.getValue(HewanModel.class);
                                hewanModels.add(hewan);
                            }
                            data.setValue(hewanModels);
                        } else {
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<List<String>> getListtab(String param) {
        final MutableLiveData<List<String>> data = new MutableLiveData<>();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_RENCANA)
                .orderByChild(Const.CHILD_JENIS_HEWAN_RENCANA)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<String> s = new ArrayList<>();
                            for (DataSnapshot d : snapshot.getChildren()) {
                                String val = d.getKey();
                                s.add(val);
                            }
                            data.setValue(s);
                        } else {
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
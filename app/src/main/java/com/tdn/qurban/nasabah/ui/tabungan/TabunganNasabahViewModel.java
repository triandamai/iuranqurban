package com.tdn.qurban.nasabah.ui.tabungan;

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
import com.tdn.domain.model.TabunganModel;
import com.tdn.domain.model.hewanModel;

import java.util.ArrayList;
import java.util.List;

public class TabunganNasabahViewModel extends ViewModel {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public LiveData<List<TabunganModel>> listLiveData;


    public TabunganNasabahViewModel() {
        listLiveData = getTabungan();
    }

    public LiveData<List<TabunganModel>> getTabungan() {
        final MutableLiveData<List<TabunganModel>> tabunganModel = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_TABUNGAN)
                .orderByChild(Const.CHILD_ORDERBYUID)
                .startAt(firebaseAuth.getCurrentUser().getUid())
                .endAt(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<TabunganModel> hewanModelList = new ArrayList<>();

                            for (DataSnapshot data : snapshot.getChildren()) {

                                TabunganModel hewanModel = data.getValue(TabunganModel.class);
                                assert hewanModel != null;
                                hewanModel.setId(data.getKey());

                                hewanModelList.add(hewanModel);

                            }
                            tabunganModel.setValue(hewanModelList);

                        } else {
                            tabunganModel.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tabunganModel.setValue(null);
                    }
                });
        return tabunganModel;
    }

}
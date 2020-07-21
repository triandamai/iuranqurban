package com.tdn.qurban.admin.ui.tabungan;

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
import com.tdn.domain.model.TabunganModel;

import java.util.List;

public class TabunganAdminViewModel extends ViewModel {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private LiveData<List<TabunganModel>> tabunganData;

    public TabunganAdminViewModel() {
        this.tabunganData = new MutableLiveData<>();
        getAllTabungan();
    }

    public LiveData<List<TabunganModel>> getTabunganData() {
        if (tabunganData == null) {
            tabunganData = new MutableLiveData<>();
        }
        return tabunganData;
    }

    private void getAllTabungan() {
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_TABUNGAN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot data : snapshot.getChildren()) {
                                TabunganModel model = data.getValue(TabunganModel.class);
                                model.setId(data.getKey());
                                tabunganData.getValue().add(model);
                            }
                        } else {
                            tabunganData = new MutableLiveData<>();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tabunganData = new MutableLiveData<>();
                    }
                });
    }
}
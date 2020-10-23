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
import com.tdn.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class TambahTabunganViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public LiveData<List<UserModel>> listUser;

    public TambahTabunganViewModel() {
        this.listUser = getAllUser();
    }

    private LiveData<List<UserModel>> getAllUser() {
    final MutableLiveData<List<UserModel>> data = new MutableLiveData<>();
    databaseReference.child(Const.BASE_CHILD)
            .child(Const.CHILD_USER)
            .orderByChild(Const.CHILD_USER_LEVEL)
            .startAt(Const.USER_LEVEL_NASABAH)
            .endAt(Const.USER_LEVEL_NASABAH)
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        List<UserModel> u = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            UserModel userModel = ds.getValue(UserModel.class);
                            userModel.setUid(ds.getKey());
                            u.add(userModel);
                        }
                        data.setValue(u);
                    }else {
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
package com.tdn.qurban.admin.ui.nasabah;

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

public class NasabahViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private ValueEventListener datanasabahListener;
    private LiveData<List<UserModel>> useListLiveData;

    public NasabahViewModel() {
        getAllNasabah();
    }

    private void getAllNasabah() {
        datanasabahListener = databaseReference.child(Const.CHILD_USER)
                .orderByChild(Const.CHILD_USER_LEVEL)
                .startAt(Const.USER_LEVEL_NASABAH)
                .endAt(Const.USER_LEVEL_NASABAH).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<UserModel> userModelList = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {
                                UserModel userModel = data.getValue(UserModel.class);
                                userModel.setUid(data.getKey());
                                userModelList.add(userModel);
                            }
                            userModelList.addAll(userModelList);
                        } else {
                            useListLiveData = new MutableLiveData<>();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        useListLiveData = new MutableLiveData<>();
                    }
                });
        databaseReference.addValueEventListener(datanasabahListener);
    }

    public LiveData<List<UserModel>> getUseListLiveData() {
        if (useListLiveData == null) {
            useListLiveData = new MutableLiveData<>();
        }
        return useListLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(datanasabahListener);
    }
}
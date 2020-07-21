package com.tdn.qurban.admin.ui.detailnasabah;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.rencanaModel;
import com.tdn.domain.model.UserModel;

public class DetailNasabahViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private MutableLiveData<UserModel> detailUsers;
    private MutableLiveData<rencanaModel> rencanaModelMutableLiveData;

    public DetailNasabahViewModel(Context context, String id) {
        this.detailUsers = new MutableLiveData<>();
        this.rencanaModelMutableLiveData = new MutableLiveData<>();
        if (id != null) {
            getDetail(id);
        }
    }

    private void getDetail(String id) {
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel userModel = snapshot.getValue(UserModel.class);
                            detailUsers.setValue(userModel);
                        } else {
                            detailUsers.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        detailUsers.setValue(null);
                    }
                });
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_RENCANA)
                .child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            rencanaModel rencanaModel = snapshot.getValue(rencanaModel.class);
                            rencanaModel.setUid(snapshot.getKey());
                            rencanaModelMutableLiveData.setValue(rencanaModel);
                        } else {
                            rencanaModelMutableLiveData = new MutableLiveData<>();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        rencanaModelMutableLiveData = new MutableLiveData<>();

                    }
                });
    }

    public MutableLiveData<UserModel> getDetailUsers() {
        if (detailUsers == null) {
            detailUsers = new MutableLiveData<>();
        }
        return detailUsers;
    }

    public MutableLiveData<rencanaModel> getRencanaModelMutableLiveData() {
        if (rencanaModelMutableLiveData == null) {
            rencanaModelMutableLiveData = new MutableLiveData<>();
        }
        return rencanaModelMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
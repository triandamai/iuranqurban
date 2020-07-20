package com.tdn.qurban.admin.ui.detailnasabah;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.userModel;

public class DetailNasabahViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private ValueEventListener detailNasabah;
    private MutableLiveData<userModel> detailUsers;

    public DetailNasabahViewModel(ValueEventListener detailNasabah, MutableLiveData<userModel> detailUsers) {
        this.detailNasabah = detailNasabah;
        getDetail();
    }

    private void getDetail() {
        detailNasabah = databaseReference.child(Const.CHILD_USER).child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.addValueEventListener(detailNasabah);
    }

    public MutableLiveData<userModel> getDetailUsers() {
        return detailUsers;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(detailNasabah);
    }
}
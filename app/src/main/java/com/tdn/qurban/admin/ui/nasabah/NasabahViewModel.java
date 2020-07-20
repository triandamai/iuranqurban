package com.tdn.qurban.admin.ui.nasabah;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;

public class NasabahViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private ValueEventListener dataNasabah;

    public NasabahViewModel() {
        getAllNasabah();
    }

    private void getAllNasabah() {
        dataNasabah = databaseReference.child(Const.CHILD_USER)
                .orderByChild("")
                .startAt(Const.USER_LEVEL_NASABAH)
                .endAt(Const.USER_LEVEL_NASABAH).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        databaseReference.addValueEventListener(dataNasabah);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(dataNasabah);
    }
}
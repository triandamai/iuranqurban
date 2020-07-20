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
    private ValueEventListener detailNasabahListener;
    private MutableLiveData<userModel> detailUsers;

    public DetailNasabahViewModel(ValueEventListener detailNasabahListener, MutableLiveData<userModel> detailUsers) {
        this.detailNasabahListener = detailNasabahListener;
        getDetail();
    }

    private void getDetail() {
        detailNasabahListener = databaseReference.child(Const.CHILD_USER).child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.addValueEventListener(detailNasabahListener);
    }

    public MutableLiveData<userModel> getDetailUsers() {
        if (detailUsers == null) {
            detailUsers = new MutableLiveData<>();
        }
        return detailUsers;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(detailNasabahListener);
    }
}
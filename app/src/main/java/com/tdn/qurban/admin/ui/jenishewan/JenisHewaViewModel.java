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
import com.tdn.domain.model.hewanModel;

import java.util.List;

public class JenisHewaViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Const.BASE_CHILD);
    private ValueEventListener jenishewanListener;
    private LiveData<List<hewanModel>> jenisHewan;

    public JenisHewaViewModel(DatabaseReference databaseReference, ValueEventListener jenishewanListener, LiveData<List<hewanModel>> jenisHewan) {
        this.databaseReference = databaseReference;
        this.jenishewanListener = jenishewanListener;
        this.jenisHewan = jenisHewan;
        getJenisHewan();
    }

    private void getJenisHewan() {
        jenishewanListener = databaseReference.child(Const.CHILD_HEWAN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.addValueEventListener(jenishewanListener);

    }

    public LiveData<List<hewanModel>> getJenisHewaViewModel() {
        if (jenisHewan == null) {
            jenisHewan = new MutableLiveData<>();
        }
        return jenisHewan;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(jenishewanListener);
    }
}
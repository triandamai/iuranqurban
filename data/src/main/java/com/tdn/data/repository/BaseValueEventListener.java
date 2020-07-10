package com.tdn.data.repository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.mapper.FirebaseMapper;

import java.util.List;

public class BaseValueEventListener<Model,Entity> implements ValueEventListener {
    private FirebaseMapper<Entity, Model> mapper;
    private FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<Model> callback;

    public BaseValueEventListener(FirebaseMapper<Entity, Model> mapper,
                                  FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<Model> callback) {
        this.mapper = mapper;
        this.callback = callback;
    }
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
       // Log.e("BAse Listener",dataSnapshot.toString());
        List<Model> data = mapper.mapList(dataSnapshot);
        callback.onSuccess(data);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        callback.onError(databaseError.toException());
    }
}

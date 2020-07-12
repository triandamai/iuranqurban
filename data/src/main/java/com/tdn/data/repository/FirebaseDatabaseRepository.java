package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.tdn.data.Const;
import com.tdn.data.mapper.FirebaseMapper;

import java.util.List;

public abstract class FirebaseDatabaseRepository<Model> {
    protected DatabaseReference databaseReference;
    private FirebaseDatabaseRepositoryCallback<Model> firebaseCallback;

    private BaseValueEventListener listener;
    private FirebaseMapper mapper;


    protected abstract DatabaseReference getDb();

    public FirebaseDatabaseRepository(FirebaseMapper mapper) {

        databaseReference = getDb();
        this.mapper = mapper;
    }


    public void addListener(FirebaseDatabaseRepositoryCallback<Model> firebaseCallback) {
        this.firebaseCallback = firebaseCallback;
        listener = new BaseValueEventListener(mapper, firebaseCallback);
        databaseReference.addValueEventListener(listener);
    }

    public void removeListener() {
        databaseReference.removeEventListener(listener);
    }

    public interface FirebaseDatabaseRepositoryCallback<T> {
        void onSuccess(List<T> result);

        void onError(Exception e);
    }
}

package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdn.data.Const;
import com.tdn.data.mapper.tabunganMapper;
import com.tdn.data.mapper.userMapper;
import com.tdn.domain.model.tabunganModel;
import com.tdn.domain.model.userModel;

public class tabunganRepository extends FirebaseDatabaseRepository<tabunganModel> {

    DatabaseReference databaseReference;

    public tabunganRepository(DatabaseReference databaseReference) {
        super(new tabunganMapper());
        this.databaseReference = databaseReference;

    }


    @Override
    protected DatabaseReference getDb() {
        return databaseReference;
    }
}

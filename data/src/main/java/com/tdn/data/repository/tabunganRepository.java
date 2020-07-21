package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.mapper.tabunganMapper;
import com.tdn.domain.model.TabunganModel;

public class tabunganRepository extends FirebaseDatabaseRepository<TabunganModel> {

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

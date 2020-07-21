package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.mapper.saldoMapper;
import com.tdn.domain.model.saldoModel;

public class saldoRepository extends FirebaseDatabaseRepository<saldoModel> {
    DatabaseReference databaseReference;

    public saldoRepository(DatabaseReference databaseReference) {
        super(new saldoMapper());
        this.databaseReference = databaseReference;
    }


    @Override
    protected DatabaseReference getDb() {
        return databaseReference;
    }
}

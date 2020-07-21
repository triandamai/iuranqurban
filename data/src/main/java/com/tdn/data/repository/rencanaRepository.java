package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.mapper.rencanaMapper;
import com.tdn.domain.model.rencanaModel;

public class rencanaRepository extends FirebaseDatabaseRepository<rencanaModel> {
    public rencanaRepository() {
        super(new rencanaMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}

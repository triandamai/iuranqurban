package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.Const;
import com.tdn.data.mapper.rencanaMapper;
import com.tdn.data.mapper.userMapper;
import com.tdn.domain.model.rencanaModel;
import com.tdn.domain.model.userModel;

public class rencanaRepository extends FirebaseDatabaseRepository<rencanaModel> {
    public rencanaRepository() {
        super(new rencanaMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}
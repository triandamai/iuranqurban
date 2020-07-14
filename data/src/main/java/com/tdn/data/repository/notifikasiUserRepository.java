package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.Const;
import com.tdn.data.mapper.notifikasiMapper;
import com.tdn.domain.model.notifikasiModel;

public class notifikasiUserRepository extends FirebaseDatabaseRepository<notifikasiModel> {
    public notifikasiUserRepository() {
        super(new notifikasiMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}

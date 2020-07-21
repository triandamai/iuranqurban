package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.mapper.notifikasiMapper;
import com.tdn.domain.model.NotifikasiModel;

public class notifikasiUserRepository extends FirebaseDatabaseRepository<NotifikasiModel> {
    public notifikasiUserRepository() {
        super(new notifikasiMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}

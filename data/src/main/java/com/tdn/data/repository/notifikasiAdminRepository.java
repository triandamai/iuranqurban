package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.mapper.notifikasiMapper;
import com.tdn.domain.model.NotifikasiModel;

public class notifikasiAdminRepository extends FirebaseDatabaseRepository<NotifikasiModel> {
    public notifikasiAdminRepository() {
        super(new notifikasiMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}

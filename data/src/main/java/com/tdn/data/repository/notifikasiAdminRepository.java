package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.Const;
import com.tdn.data.mapper.notifikasiMapper;
import com.tdn.data.mapper.userMapper;
import com.tdn.domain.model.notifikasiModel;
import com.tdn.domain.model.userModel;

public class notifikasiAdminRepository extends FirebaseDatabaseRepository<notifikasiModel> {
    public notifikasiAdminRepository() {
        super(new notifikasiMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}

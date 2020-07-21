package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.mapper.hewanMapper;
import com.tdn.domain.model.hewanModel;

public class hewanRepository extends FirebaseDatabaseRepository<hewanModel> {
    public hewanRepository() {
        super(new hewanMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}

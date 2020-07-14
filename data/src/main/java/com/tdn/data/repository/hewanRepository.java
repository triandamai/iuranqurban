package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.Const;
import com.tdn.data.mapper.hewanMapper;
import com.tdn.data.mapper.userMapper;
import com.tdn.domain.model.hewanModel;
import com.tdn.domain.model.userModel;

public class hewanRepository extends FirebaseDatabaseRepository<hewanModel> {
    public hewanRepository() {
        super(new hewanMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}

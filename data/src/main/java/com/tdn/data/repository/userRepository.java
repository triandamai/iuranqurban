package com.tdn.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.tdn.data.mapper.userMapper;
import com.tdn.domain.model.UserModel;

public class userRepository extends FirebaseDatabaseRepository<UserModel> {
    public userRepository(DatabaseReference databaseReference) {
        super(new userMapper());
    }


    @Override
    protected DatabaseReference getDb() {
        return null;
    }
}

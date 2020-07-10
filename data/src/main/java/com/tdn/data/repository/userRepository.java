package com.tdn.data.repository;

import com.tdn.data.Const;
import com.tdn.data.mapper.userMapper;
import com.tdn.domain.model.userModel;

public class userRepository extends FirebaseDatabaseRepository<userModel>{
    public userRepository() {
        super(new userMapper());
    }

    @Override
    protected String getRootNode() {
        return Const.CHILD_USER;
    }
}

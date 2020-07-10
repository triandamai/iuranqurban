package com.tdn.data.repository;

import com.tdn.data.Const;
import com.tdn.data.mapper.tabunganMapper;
import com.tdn.data.mapper.userMapper;
import com.tdn.domain.model.tabunganModel;
import com.tdn.domain.model.userModel;

public class tabunganRepository extends FirebaseDatabaseRepository<tabunganModel>{
    public tabunganRepository() {
        super(new tabunganMapper());
    }

    @Override
    protected String getRootNode() {
        return Const.CHILD_TABUNGAN;
    }
}

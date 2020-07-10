package com.tdn.data.repository;

import com.tdn.data.Const;
import com.tdn.data.mapper.saldoMapper;
import com.tdn.data.mapper.userMapper;
import com.tdn.domain.model.saldoModel;
import com.tdn.domain.model.userModel;

public class saldoRepository extends FirebaseDatabaseRepository<saldoModel>{
    public saldoRepository() {
        super(new saldoMapper());
    }

    @Override
    protected String getRootNode() {
        return Const.CHILD_SALDO;
    }
}

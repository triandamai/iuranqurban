package com.tdn.data.mapper;

import com.tdn.data.entity.saldoEntity;
import com.tdn.domain.model.saldoModel;

public class saldoMapper extends FirebaseMapper<saldoEntity,saldoModel> {
    @Override
    public saldoModel map(saldoEntity e, String id) {
        saldoModel m = new saldoModel();
        m.setJumlah(e.getJumlah());
        m.setLast_updated(e.getLast_updated());
        m.setUid(id);
        return m;
    }
}

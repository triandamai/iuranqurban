package com.tdn.data.mapper;

import com.tdn.data.entity.saldoEntity;
import com.tdn.domain.model.SaldoModel;

public class saldoMapper extends FirebaseMapper<saldoEntity, SaldoModel> {
    @Override
    public SaldoModel map(saldoEntity e, String id) {
        SaldoModel m = new SaldoModel();
        m.setJumlah(e.getJumlah());
        m.setLast_updated(e.getLast_updated());
        m.setUid(id);
        return m;
    }
}

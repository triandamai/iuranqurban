package com.tdn.data.mapper;

import com.tdn.data.entity.rencanaEntity;
import com.tdn.domain.model.rencanaModel;

public class rencanaMapper extends FirebaseMapper<rencanaEntity, rencanaModel> {
    @Override
    public rencanaModel map(rencanaEntity e, String id) {
        rencanaModel m = new rencanaModel();
        m.setCreated_at(e.getCreated_at());
        m.setJenis(e.getJenis());
        m.setJumlah(e.getJumlah());
        m.setPembelian(e.getPembelian());
        m.setTarget_nominal(e.getTarget_nominal());
        m.setTempat_penyerahan(e.getTempat_penyerahan());
        m.setUid(id);
        m.setUpdated_at(e.getUpdated_at());
        return m;
    }
}

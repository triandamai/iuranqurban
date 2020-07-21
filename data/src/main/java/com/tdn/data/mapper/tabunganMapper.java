package com.tdn.data.mapper;

import com.tdn.data.entity.tabunganEntity;
import com.tdn.domain.model.TabunganModel;

public class tabunganMapper extends FirebaseMapper<tabunganEntity, TabunganModel> {
    @Override
    public TabunganModel map(tabunganEntity e, String id) {
        TabunganModel m = new TabunganModel();
        m.setBukti(e.getBukti());
        m.setCreated_at(e.getCreated_at());
        m.setId(id);
        m.setKeterangan(e.getKeterangan());
        m.setNominal(e.getNominal());
        m.setStatus(e.getStatus());
        m.setUid(e.getUid());
        m.setUpdated_at(e.getUpdated_at());

        return m;
    }
}

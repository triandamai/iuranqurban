package com.tdn.data.mapper;

import com.tdn.data.entity.tabunganEntity;
import com.tdn.domain.model.tabunganModel;

public class tabunganMapper extends FirebaseMapper<tabunganEntity, tabunganModel> {
    @Override
    public tabunganModel map(tabunganEntity e, String id) {
        tabunganModel m = new tabunganModel();
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

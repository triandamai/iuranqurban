package com.tdn.data.mapper;

import com.tdn.data.entity.hewanEntity;
import com.tdn.domain.model.hewanModel;

public class hewanMapper extends FirebaseMapper<hewanEntity, hewanModel> {
    @Override
    public hewanModel map(hewanEntity e, String id) {
        hewanModel m = new hewanModel();
        m.setId(id);
        m.setJenis(e.getJenis());
        m.setNominal(e.getNominal());
        return m;
    }
}

package com.tdn.data.mapper;

import com.tdn.data.entity.notifikasiEntity;
import com.tdn.domain.model.notifikasiModel;

public class notifikasiMapper extends FirebaseMapper<notifikasiEntity, notifikasiModel> {
    @Override
    public notifikasiModel map(notifikasiEntity e, String id) {
        notifikasiModel m = new notifikasiModel();
        m.setBody(e.getBody());
        m.setBroad_to(e.getBroad_to());
        m.setCreated_at(e.getCreated_at());
        m.setFrom_uid(e.getFrom_uid());
        m.setId(id);
        m.setTipe(e.getTipe());
        return m;
    }
}

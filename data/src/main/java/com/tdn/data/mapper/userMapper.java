package com.tdn.data.mapper;

import com.tdn.data.entity.userEntity;
import com.tdn.domain.model.UserModel;

public class userMapper extends FirebaseMapper<userEntity, UserModel> {
    @Override
    public UserModel map(userEntity e, String id) {
        UserModel m = new UserModel();
        m.setUid(id);
        m.setNama(e.getNama());
        m.setAlamat(e.getAlamat());
        m.setCreated_at(e.getCreated_at());
        m.setHp_wa(e.getHp_wa());
        m.setHubungan_dengan_ahli_waris(e.getHubungan_dengan_ahli_waris());
        m.setJenis_kelamin(e.getJenis_kelamin());
        m.setKartu_identitas(e.getKartu_identitas());
        m.setLevel(e.getLevel());
        m.setNama_ahli_waris(e.getNama_ahli_waris());
        m.setNik(e.getNik());
        m.setStatus(e.getStatus());
        m.setNo_hp(e.getNo_hp());
        m.setUpdated_at(e.getUpdated_at());
        return m;
    }
}

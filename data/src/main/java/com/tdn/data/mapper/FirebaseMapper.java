package com.tdn.data.mapper;


import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class FirebaseMapper<Entity,Model> implements IMapper<Entity , Model,String> {

    public Model map(DataSnapshot dataSnapshot) {
        Entity ety = dataSnapshot.getValue(getEntityClass());

        return map(ety,dataSnapshot.getKey());
    }

    public List<Model> mapList(DataSnapshot dataSnapshot){
        List<Model> list = new ArrayList<>();

        for (DataSnapshot item : dataSnapshot.getChildren()){
            list.add(map(item));
        }
        return list;
    }
    @SuppressWarnings("unchecked")
    private Class<Entity> getEntityClass(){
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();

        return (Class<Entity>) superClass.getActualTypeArguments()[0];
    }
}

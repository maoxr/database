package com.momo.springboot.database.first.repository;

import com.momo.springboot.database.base.BaseRepository;
import com.momo.springboot.database.first.First;

import javax.persistence.Query;


public interface FirstRepository extends BaseRepository<First, String> {
    public default void saveByEntity(First first) {
        getEntityManager().persist(first);
    }

    public default void updateByHql(String id, String name) {
        String hql = "update First as f set f.name = ?1 where f.id = ?2";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter(1,name);
        query.setParameter(2,id);
        query.executeUpdate();
    }


}

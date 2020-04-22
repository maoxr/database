package com.momo.springboot.database.second.repository;

import com.momo.springboot.database.base.BaseRepository;
import com.momo.springboot.database.second.Second;

import javax.persistence.Query;

public interface SecondRepository extends BaseRepository<Second, String> {

    public default void saveByEntity(Second second) {
        getEntityManager().persist(second);
    }

    public default void delBySql(String id) {
        String sql = "delete from t_second where id = ?";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public default void updateBySql(String id, String name) {
        String sql = "update t_second set code = ? where id = ?";
            Query query = getEntityManager().createNativeQuery(sql);
            query.setParameter(1, name).setParameter(2,id);
            query.executeUpdate();


    }
}

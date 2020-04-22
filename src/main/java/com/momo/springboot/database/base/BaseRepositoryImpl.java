package com.momo.springboot.database.base;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


//    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
//        super(domainClass, em);
//        this.entityManager = em;
//    }

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


}

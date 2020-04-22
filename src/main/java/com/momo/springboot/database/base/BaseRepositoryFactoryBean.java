package com.momo.springboot.database.base;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryFactoryBean<T extends Repository<S, ID>, S, ID> extends JpaRepositoryFactoryBean<T, S, ID> {
    public BaseRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }


    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }

    private static class BaseRepositoryFactory<S, ID extends Serializable> extends JpaRepositoryFactory {
        private EntityManager em;

        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.em = entityManager;
        }

        @Override
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {

            JpaEntityInformation<?, Serializable> entityInformation = this.getEntityInformation(information.getDomainType());
            Object repository = this.getTargetRepositoryViaReflection(information, new Object[]{entityInformation, entityManager});
            Assert.isInstanceOf(BaseRepositoryImpl.class, repository);
            return (JpaRepositoryImplementation) repository;

        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }


}

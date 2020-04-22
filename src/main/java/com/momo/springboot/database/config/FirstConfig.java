package com.momo.springboot.database.config;

import com.momo.springboot.database.base.BaseRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "firstEntityManagerFactory",
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class,
        transactionManagerRef = "firstTransactionManager",
        basePackages = {"com.momo.springboot.database.first"}) // 设置Repository所在位置
public class FirstConfig {
    @Autowired
    @Qualifier("firstDataSource")
    private DataSource firstDataSource;

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;


    @Primary
    @Bean(name = "firstEntityManager")
    public EntityManager firstEntityManager(EntityManagerFactoryBuilder builder) {
        return firstEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "firstEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.
                dataSource(firstDataSource)
                .properties(properties)
                .packages("com.momo.springboot.database.first")
                .persistenceUnit("firstPersistenceUnit")
                .build();//实体包路径

    }

    @Primary
    @Bean(name = "firstTransactionManager")
    public PlatformTransactionManager firstTransactionManager(@Qualifier("firstEntityManagerFactory") LocalContainerEntityManagerFactoryBean firstEntityManagerFactory) {
        return new JpaTransactionManager(firstEntityManagerFactory.getObject());
    }


}

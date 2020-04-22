package com.momo.springboot.database.config;

import com.momo.springboot.database.base.BaseRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "secondEntityManagerFactory", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class, transactionManagerRef = "secondTransactionManager", basePackages = {
        "com.momo.springboot.database.second" }) // 设置Repository所在位置
@EntityScan(basePackages = "com.momo.springboot.database.second")
public class SecondConfig {

    @Autowired
    @Qualifier("secondDataSource")
    private DataSource secondDataSource;

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;


    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());

        return builder.
                dataSource(secondDataSource)
                .properties(properties)
                .packages("com.momo.springboot.database.second")
                .persistenceUnit("secondPersistenceUnit")
                .build();//实体包路径
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(@Qualifier("secondEntityManagerFactory") LocalContainerEntityManagerFactoryBean secondEntityManagerFactory) {
        return new JpaTransactionManager(secondEntityManagerFactory.getObject());
    }
}

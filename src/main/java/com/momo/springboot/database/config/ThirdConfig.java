package com.momo.springboot.database.config;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "thirdEntityManagerFactory", transactionManagerRef = "thirdTransactionManager", basePackages = {
        "com.momo.springboot.database.third" }) // 设置Repository所在位置
@EntityScan(basePackages = "com.momo.springboot.database.third")
public class ThirdConfig {

    @Autowired
    @Qualifier("thirdDataSource")
    private DataSource thirdDataSource;

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "thirdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.
                dataSource(thirdDataSource)
                .properties(properties)
                .packages("com.momo.springboot.database.third")
                .persistenceUnit("thirdPersistenceUnit")
                .build();//实体包路径

    }

    @Bean(name = "thirdTransactionManager")
    public PlatformTransactionManager thirdTransactionManager(@Qualifier("thirdEntityManagerFactory") LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory) {
        return new JpaTransactionManager(thirdEntityManagerFactory.getObject());
    }

    @Bean(name = "thirdJdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(thirdDataSource);
    }

}

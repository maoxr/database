package com.momo.springboot.database.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "firstDataSource")
    @Qualifier("firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DataSource firstDataSource() {
        System.out.println("primary db built");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondDataSource")
    @Qualifier("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource secondDataSource() {
        System.out.println("secondary db built");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "thirdDataSource")
    @Qualifier("thirdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test3")
    public DataSource thirdDataSource() {
        System.out.println("third db built");
        return DataSourceBuilder.create().build();
    }


}

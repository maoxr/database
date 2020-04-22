package com.momo.springboot.database.third.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ThirdRepository {
    @Autowired
    @Qualifier(value = "thirdJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void save(String id, String sex) {
        jdbcTemplate.batchUpdate("insert into t_third values ('" + id + "','" + sex + "')");
    }
}

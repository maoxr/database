package com.momo.springboot.database.second;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author guoyu.huang
 */
@Setter
@Getter
@Entity(name = "t_second")
public class Second {

    @Id
    @Column(name = "id", columnDefinition = "char(32) comment '主键'")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @GeneratedValue(generator = "paymentableGenerator")
    protected String id;

    @Column(name = "code", columnDefinition = "varchar(255) comment 'name'")
    private String code;

}
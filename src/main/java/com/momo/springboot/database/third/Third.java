package com.momo.springboot.database.third;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "t_third")
public class Third {

    @Id
    @Column(name = "id", columnDefinition = "char(32) comment '主键'")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @GeneratedValue(generator = "paymentableGenerator")
    protected String id;

    @Column(name = "sex", columnDefinition = "varchar(255) comment 'name'")
    private String sex;

}

package com.momo.springboot.database.first;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "t_first")
public class First {

    @Id
    @Column(name = "id", columnDefinition = "char(32) comment '主键'")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @GeneratedValue(generator = "paymentableGenerator")
    protected String id;

    @Column(name = "name", columnDefinition = "varchar(255) comment 'name'")
    private String name;
}

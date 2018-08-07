package com.eric.entities;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//@AllArgsConstructor //全参构造体
@NoArgsConstructor  //无参构造体
@Data
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long deptno; // 主键
    private String dname; // 部门名称
    private String db_source;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库

    // 测试熔断时使用，无其他意义
    public Dept(String dname) {
        super();
        this.dname = dname;
    }
}

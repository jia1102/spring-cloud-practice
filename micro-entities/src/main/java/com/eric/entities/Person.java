package com.eric.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor  //无参构造体
@Data
@Accessors(chain = true)
public class Person {

    private String id;

    private String name;

    private Integer age;

    private String work;
}

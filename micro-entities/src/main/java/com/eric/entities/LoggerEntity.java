package com.eric.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor //全参构造体
@NoArgsConstructor  //无参构造体
@Data
@Accessors(chain = true)
public class LoggerEntity {
    private String method;
    private String uri;
    private Object[] args;
    private Object result;
    private String operator;
    private String appName;
}

package com.eric.controller;

import com.eric.entities.Dept;
import com.eric.service.DeptConsumerFeignClient;
import com.eric.service.DeptConsumerFeignWithoutFactoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    @Autowired
    DeptConsumerFeignClient deptConsumerFeignClient;

    @Autowired
    DeptConsumerFeignWithoutFactoryClient deptConsumerFeignWithoutFactoryClient;

    @GetMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return deptConsumerFeignClient.add(dept);
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptConsumerFeignClient.get(id);
    }

    @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
        return deptConsumerFeignClient.list();
    }

    @GetMapping("/consumer/fallback/dept/list")
    public List<Dept> listFallBack(){
        return deptConsumerFeignWithoutFactoryClient.list();
    }
}

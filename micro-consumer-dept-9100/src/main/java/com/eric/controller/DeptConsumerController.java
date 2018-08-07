package com.eric.controller;

import com.eric.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    @Value("${user.userServicePath}")
    private String userServicePath;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(userServicePath + "/dept/add", dept, Boolean.class);
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(userServicePath + "/dept/get/" + id, Dept.class, Boolean.class);
    }

    @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(userServicePath + "/dept/list", List.class);
    }
}

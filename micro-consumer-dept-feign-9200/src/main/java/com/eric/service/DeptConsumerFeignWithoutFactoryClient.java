package com.eric.service;

import com.eric.entities.Dept;
import feign.RequestLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "MICRO-PROVIDER-DEPT", fallback = DeptConsumerFeignWithoutFactoryClient.DeptConsumerFeignClientFallback.class)
public interface DeptConsumerFeignWithoutFactoryClient {

//    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @RequestLine("GET /dept/get/{id}")
    public Dept get(@PathVariable("id") long id);

//    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    @RequestLine("GET /dept/list")
    public List<Dept> list();

//    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    @RequestLine("POST /dept/add")
    public boolean add(Dept dept);

    @Component
    class DeptConsumerFeignClientFallback implements DeptConsumerFeignWithoutFactoryClient {
        @Override
        public Dept get(long id) {
            return new Dept().setDeptno(id)
                    .setDname("该ID：" + id + "没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭")
                    .setDb_source("No this database in MySql");
        }

        @Override
        public List<Dept> list() {

            Dept dept =new Dept().setDeptno(1l)
                    .setDname("该ID：" + 1l + "没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭")
                    .setDb_source("No this database in MySql");

            List list = new ArrayList();
            list.add(dept);
            return list;
        }

        @Override
        public boolean add(Dept dept) {
            return false;
        }
    }
}


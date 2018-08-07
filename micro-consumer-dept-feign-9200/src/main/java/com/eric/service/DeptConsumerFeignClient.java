package com.eric.service;

import com.eric.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "MICRO-PROVIDER-DEPT", fallbackFactory = DeptConsumerFeignClient.DeptConsumerFeignClientFallbackFactory.class)
public interface DeptConsumerFeignClient {

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(Dept dept);

    @Component
    class DeptConsumerFeignClientFallbackFactory implements FallbackFactory<DeptConsumerFeignClient> {
        @Override
        public DeptConsumerFeignClient create(Throwable cause) {
            return new DeptConsumerFeignClient() {

                @Override
                public Dept get(long id) {
                    return new Dept().setDeptno(id)
                            .setDname("该ID：" + id + "没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭")
                            .setDb_source("No this database in MySql");
                }

                @Override
                public List<Dept> list() {
                    return null;
                }

                @Override
                public boolean add(Dept dept) {
                    return false;
                }
            };
        }
    }
}

package com.eric.service;

import com.eric.entities.Dept;
import feign.RequestLine;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @Des: 很多场景下，需要了解回退的原因，此时可使用注解@FeignClient的fallbackFactory属性，为Feign打印回退日志。
 */
@FeignClient(value = "MICRO-PROVIDER-DEPT", fallbackFactory = DeptConsumerFeignClient.DeptConsumerFeignClientFallbackFactory.class)
public interface DeptConsumerFeignClient {

//    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @RequestLine("GET /dept/get/{id}")
    public Dept get(@PathVariable("id") long id);

//    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    @RequestLine("GET /dept/list")
    public List<Dept> list();

//    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    @RequestLine("POST /dept/add")
    public boolean add(Dept dept);


    /**
     * DeptConsumerFeignClient的fallbackFactory类，该类需要实现FallbackFactory接口，并且覆写create方法
     * The fallback factory must produce instances of fallback classes that implement the interface annotated by {@link FeignClient}
     */
    @Component
    class DeptConsumerFeignClientFallbackFactory implements FallbackFactory<DeptConsumerFeignClient> {

        private static final Logger LOGGER = LoggerFactory.getLogger(DeptConsumerFeignClientFallbackFactory.class);

        @Override
        public DeptConsumerFeignClient create(Throwable cause) {
            return new DeptConsumerFeignClient() {

                @Override
                public Dept get(long id) {
                    // 日志最好放在各个fallback中，而不要直接放在create中，否则在引用启动时，就会打印该日志。
                    DeptConsumerFeignClientFallbackFactory.LOGGER.info("fallback; reason was: {}, {}", cause.getMessage(), cause);
                    return new Dept().setDeptno(id)
                            .setDname("该ID：" + id + "没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭")
                            .setDb_source("No this database in MySql");
                }

                @Override
                public List<Dept> list() {
                    // 日志最好放在各个fallback中，而不要直接放在create中，否则在引用启动时，就会打印该日志。
                    DeptConsumerFeignClientFallbackFactory.LOGGER.info("fallback; reason was: {}, {}", cause.getMessage(), cause);

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
            };
        }
    }
}

package com.eric;

import com.eric.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroSpringbootDemoApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testLog() {
        /**
         * 日志级别，从上到下
         * 可以调整日志的输出级别
         */
        logger.trace("这是trace日志。。。");
        logger.debug("这是debug日志。。。");
        logger.info("这是info日志。。。");
        logger.warn("这是warn日志。。。");
        logger.error("这是error日志。。。");
    }

    @Test
    public void  testHelloServie() {
        System.out.println("********************************测试开始********************************");
        boolean bl = ioc.containsBean("helloService");
        System.out.println(bl);
        System.out.println("********************************测试完成********************************");
    }

    @Test
    public void contextLoads() {
        System.out.println("********************************测试开始********************************");
        System.out.println(person);
        System.out.println("********************************测试完成********************************");
    }

}

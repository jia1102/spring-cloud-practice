package com.eric.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleConfigBean {

    @Bean
    public IRule myRule()
    {
        return new RoundRobinRule();     //默认的轮询
        // return new RandomRule();           //随机算法
        // return new RetryRule();          //在线机器轮询
    }

    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }
}

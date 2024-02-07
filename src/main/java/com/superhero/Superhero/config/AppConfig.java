package com.superhero.Superhero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.superhero.Superhero.aspect.ExecutionTimeInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.superhero.Superhero")
public class AppConfig {

    @Bean
    public ProxyFactoryBean executionTimeAspect() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames("executionTimeInterceptor");
        return proxyFactoryBean;
    }
    
    @Bean
    public ExecutionTimeInterceptor executionTimeInterceptor() {
        return new ExecutionTimeInterceptor();
    }
}

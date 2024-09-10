package com.cqu.sso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截地址栏直接访问主页面和AB页面
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/main.html","/a.html","/b.html");
    }
}

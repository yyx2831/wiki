package com.example.demo.config;

import com.example.demo.interceptor.ActionInterceptor;
import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    @Resource
    ActionInterceptor actionInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns( // 排除不需要拦截的路径
                        "/test/**",
                        "/redis/**",
                        "/user/login",
                        "/category/all",
                        "/ebook/list",
                        "/doc/all/**",
                        "/doc/vote/**",
                        "/doc/find-content/**",
                        "/ebook-snapshot/**",
                        "/*/save",
                        "/*/delete/**",
                        "/*/reset-password"
                );

//        registry.addInterceptor(actionInterceptor)
//                .addPathPatterns( // 需要拦截的路径
//                        "/*/save",
//                        "/*/delete/**",
//                        "/*/reset-password");
    }
}

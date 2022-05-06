package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOriginPatterns("*") // 允许跨域访问的源
                .allowedHeaders(CorsConfiguration.ALL) // 允许跨域访问的头信息,*表示全部
                .allowedMethods(CorsConfiguration.ALL) // 允许跨域访问的请求方法
                .allowCredentials(true) // 是否支持安全证书
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }

}

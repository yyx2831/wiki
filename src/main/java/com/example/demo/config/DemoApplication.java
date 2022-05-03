package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

//@ComponentScan({ "com.example.demo", "com.example.demo.config" }) //多个包名写法
@ComponentScan({ "com.example.demo" })
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@EnableScheduling
@EnableAsync
public class DemoApplication {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        // 定制化日志
        SpringApplication app = new SpringApplication(DemoApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功，当前环境：" + env.getProperty("spring.profiles.active"));
        LOG.info("地址：http://localhost:" + env.getProperty("server.port"));
    }

}

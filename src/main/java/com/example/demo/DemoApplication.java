package com.example.demo;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
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

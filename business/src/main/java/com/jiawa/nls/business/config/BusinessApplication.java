package com.jiawa.nls.business.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan("com.jiawa")
@MapperScan("com.jiawa.nls.business.mapper")
@Slf4j
@SpringBootApplication
public class BusinessApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment environment = SpringApplication.run(BusinessApplication.class, args).getEnvironment();
        log.info("启动成功！");
        log.info("测试地址：http://localhost:{}{}/hello"
                , environment.getProperty("server.port")
                , environment.getProperty("server.servlet.context-path"));
    }

}

package com.tanhua.dubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//mapper扫描
@MapperScan("com.tanhua.dubbo.mapper")
public class DubboDBServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDBServerApplication.class,args);
    }
}

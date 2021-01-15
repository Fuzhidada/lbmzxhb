package com.epoch.dentsumcgb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(value = "com.epoch.dentsumcgb.mapper")
public class DTApplication {

    public static void main(String[] args) {
        SpringApplication.run(DTApplication.class, args);
    }

}

package com.water.neptune.ets.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangmiaojie
 */
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.water.neptune.*"})
@MapperScan("com.water.neptune.ets.mapper*")
public class NeptuneEtsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeptuneEtsApiApplication.class, args);
    }

}

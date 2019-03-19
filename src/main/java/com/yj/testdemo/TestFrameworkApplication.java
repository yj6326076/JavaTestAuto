package com.yj.testdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author exyangjun003
 */
@SpringBootApplication
@EnableJpaAuditing
public class TestFrameworkApplication {
    public static void main(String[] args){
        SpringApplication.run(TestFrameworkApplication.class);
    }
}

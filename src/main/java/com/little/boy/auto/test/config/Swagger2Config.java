/*
 * this is little boy's personal project,right received by yj6326076@hotmail.com from 2021 to 2021
 */

package com.little.boy.auto.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2Config
 *
 * @author yj632
 * @since 2021-05-09
 */
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(ApiInfo.DEFAULT);
    }
}

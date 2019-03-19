package com.yj.testdemo.config;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * httpclient连接池配置
 * @author yangjun
 */
@Configuration
public class HttpPoolConfig {
    @Bean
    public PoolingHttpClientConnectionManager httpClientConnectionManager(){
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https",  SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        clientConnectionManager.setMaxTotal(80);
        clientConnectionManager.setDefaultMaxPerRoute(20);
        return clientConnectionManager;
    }
}

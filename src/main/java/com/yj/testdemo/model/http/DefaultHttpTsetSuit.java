package com.yj.testdemo.model.http;

import com.yj.testdemo.model.TestSuit;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * 默认http自动化测试套
 * @author yangjun
 */
public class DefaultHttpTsetSuit extends TestSuit {
    private static CloseableHttpClient client = HttpClientBuilder.create().build();
    static {
        try {
            client.execute(new HttpGet("http://localhost:8080"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

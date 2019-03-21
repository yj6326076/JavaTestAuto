package com.yj.testdemo.model.http;

import com.yj.testdemo.model.TestSuit;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/**
 * 高并发请求http测试
 * @author yangjun
 */
public class AsyncHttpTestSuit extends TestSuit {
    private CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
    @Override
    public boolean execute(){
        new FutureCallback<HttpResponse>(){

            @Override
            public void completed(HttpResponse result) {

            }

            @Override
            public void failed(Exception ex) {

            }

            @Override
            public void cancelled() {

            }
        };
        return false;
    }
}

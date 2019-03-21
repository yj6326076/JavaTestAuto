package com.yj.testdemo.model.http;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

/**
 * http请求回调方法
 * @author exyangjun003
 */
public class HttpCallback implements FutureCallback<HttpResponse> {
    private String url;

    public HttpCallback(String url){
        super();
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    @Override
    public void completed(HttpResponse result) {
        System.out.println(url + result.getStatusLine().getStatusCode());
    }

    @Override
    public void failed(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void cancelled() {
        System.out.println(url + "cancelled");
    }
}

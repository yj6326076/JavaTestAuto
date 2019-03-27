package com.yj.testdemo.model.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

/**
 * http请求回调方法
 * @author exyangjun003
 */
@Slf4j
public class HttpCallback implements FutureCallback<HttpResponse> {
    private String url;

    public HttpCallback(String url){
        super();
        this.url = url;
    }

    @Override
    public void completed(HttpResponse result) {
        log.info("请求地址为{},请求返回状态code为{}",url,result.getStatusLine().getStatusCode());
    }

    @Override
    public void failed(Exception ex) {
        log.error("请求{}出错：",url,ex);
    }

    @Override
    public void cancelled() {
        log.warn("请求{},被取消!!!!",url);
    }
}

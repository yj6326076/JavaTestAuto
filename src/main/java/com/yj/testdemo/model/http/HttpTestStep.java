package com.yj.testdemo.model.http;

import com.yj.testdemo.model.TestCase;
import org.apache.http.client.methods.*;

public class HttpTestStep extends TestCase {
    private String method;
    private HttpUriRequest request;
    @Override
    public boolean environmentSet(){
        switch (method){
            case "get":
                request = new HttpGet();
                break;
            case "post":
                request = new HttpPost();
                break;
            case "put":
                request = new HttpPut();
                break;
            case "delete":
                request = new HttpDelete();
                break;
            case "patch":
                request = new HttpPatch();
                break;
            case "option":
                request = new HttpOptions();
                break;
                default:
                    request = new HttpGet();
        }
        return true;
    }
}

package com.yj.testdemo.test;

import com.yj.testdemo.TestFrameworkApplication;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = TestFrameworkApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SimpleTest {
    @Resource
    private TaskExecutor taskExecutor;

    private CloseableHttpClient closeableHttpClient;
    @Test
    public void test() throws InterruptedException, IOException {
        closeableHttpClient = HttpClientBuilder.create().build();
        Scheduler scheduler = Schedulers.from(taskExecutor);
        final int j = 10;
        for(int i=0;i<50;i++){
            Observable.create(emitter -> {
                for (int i1 = 1; i1 <= j; i1++) {
                    get(i1);
                    if(i1%5==0){
                        emitter.onNext(i1);
                    }
                    if (i1%7==0){
                        emitter.onError(new Throwable("error"));
                        return;
                    }
                }
            }).subscribeOn(scheduler).subscribe(System.out::println, Throwable::printStackTrace);
        }
        Thread.sleep(1000*1000);
        closeableHttpClient.close();
    }

    private void get(int il) throws IOException {
        log.info(il+"");
        HttpGet get = new HttpGet("https://wx-hbt-sit.evergrandelife.com.cn/sit/baseservice/getBankInfo");
        CloseableHttpResponse r = closeableHttpClient.execute(get);
        log.info(r.getStatusLine().getStatusCode() + "");
    }
}

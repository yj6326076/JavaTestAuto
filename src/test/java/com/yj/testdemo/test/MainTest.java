package com.yj.testdemo.test;

import com.yj.testdemo.model.http.HttpCallback;
import com.yj.testdemo.model.rx.MyErrorConsumer;
import com.yj.testdemo.model.rx.MyNextConsumer;
import com.yj.testdemo.model.rx.MySubscriber;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jodd.net.HttpMethod;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start" + System.currentTimeMillis());
        for(int i=0;i<10;i++) {
            Flowable.create(emitter -> {
                for (int j = 0; j < 20; j++) {
                    if (j % 2 == 0) {
                        emitter.onNext(j);
                    }
                    if(j%3==2){
                        emitter.tryOnError(new FileNotFoundException("file out reach"));
                    }
                }
            }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                    .subscribe(new MyNextConsumer(),new  MyErrorConsumer());
//            Flowable.range(1, 100)
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(new MySubscriber());
        }
        Thread.sleep(1000 * 20);
//        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
//        client.start();
//        HttpCallback callback = new HttpCallback("http://www.baidu.com");
//        final CountDownLatch latch = new CountDownLatch(1);
//        for(int i=0;i<100;i++) {
//            client.execute(new HttpGet("http://www.baidu.com"), callback);
//        }
//        try {
//            latch.await();
//            client.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
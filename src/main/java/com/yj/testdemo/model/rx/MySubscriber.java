package com.yj.testdemo.model.rx;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 测试回调数据
 * @author exyangjun003
 */
public class MySubscriber implements Subscriber<Integer> {
    private Subscription sub;
    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("Start");
        sub = s;
        sub.request(1);
        System.out.println("end");
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("next -> \b" + integer + "\b<-" + System.currentTimeMillis() + "====id=>" + Thread.currentThread().getId());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sub.request(10);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("complete");
    }
}

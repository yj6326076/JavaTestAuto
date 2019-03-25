package com.yj.testdemo.model.rx;

import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理字段
 * @author yangjun
 */
@Slf4j
public class MyErrorConsumer implements Consumer<Throwable> {
    @Override
    public void accept(Throwable throwable) throws Exception {
        log.error("Executing rxAction with some exception ",throwable);
        if(throwable instanceof Exception){
            throw (Exception) throwable;
        }
    }
}

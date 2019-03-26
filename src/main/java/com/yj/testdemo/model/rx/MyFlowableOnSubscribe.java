package com.yj.testdemo.model.rx;

import com.yj.testdemo.model.TestStep;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

import java.util.List;

/**
 * @author yangjun
 */
public class MyFlowableOnSubscribe<T extends TestStep> implements FlowableOnSubscribe<TestStep> {
    private List<T> testStep;
    public MyFlowableOnSubscribe(List<T> t){
        super();
        this.testStep = t;
    }
    @Override
    public void subscribe(FlowableEmitter<TestStep> emitter) throws Exception {
        for(T t:testStep){
            t.execute();
        }
    }
}

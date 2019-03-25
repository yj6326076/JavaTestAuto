package com.yj.testdemo.model.rx;

import com.yj.testdemo.model.TestStep;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * @author yangjun
 */
public class MyFlowableOnSubscribe<T extends TestStep> implements FlowableOnSubscribe<TestStep> {
    @Override
    public void subscribe(FlowableEmitter<TestStep> emitter) throws Exception {
        emitter.isCancelled();
        emitter.requested();
    }
}

package com.yj.testdemo.model.rx;

import com.yj.testdemo.model.TestStep;
import io.reactivex.functions.Consumer;

/**
 * @author yangjun
 */
public class MyNextConsumer<T extends TestStep> implements Consumer {

    @Override
    public void accept(Object o) throws Exception {
        System.out.println(o);
    }
}

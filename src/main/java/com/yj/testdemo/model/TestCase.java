package com.yj.testdemo.model;

import lombok.Data;

import java.util.List;

/**
 * 执行用例
 * @author yangjun
 */
@Data
public class TestCase implements TestCaseInterface {

    private List<? extends TestStep> environmentSetList;
    private List<? extends TestStep> preTestList;
    private List<? extends TestStep> testStepList ;
    private List<? extends TestStep> afterTestList;
    private List<? extends TestStep> environmentDestroyList;

    @Override
    public boolean duringTest() {
        for(TestStep testStep:testStepList){
            if(!testStep.execute()){
                return false;
            }
        }
        return true;
    }
}

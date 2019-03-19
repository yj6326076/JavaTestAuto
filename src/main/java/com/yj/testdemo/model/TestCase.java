package com.yj.testdemo.model;

import lombok.Data;

import java.util.List;

/**
 * @author exyangjun003
 */
@Data
public class TestCase implements TestInterface {

    private List<TestStep> environmentSetList;
    private List<TestStep> preTestList;
    private List<TestStep> testStepList ;
    private List<TestStep> afterTestList;
    private List<TestStep> environmentDestroyList;

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

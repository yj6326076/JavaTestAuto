package com.yj.testdemo.model;

import lombok.Data;

import java.util.List;

/**
 * @author exyangjun003
 */
@Data
public class TestSuit implements TestCaseInterface {
    private List<TestStep> environmentSetList;
    private List<TestStep> preTestList;
    private List<TestCase> testCaseList;
    private List<TestStep> afterTestList;
    private List<TestStep> environmentDestroyList;

    @Override
    public boolean duringTest() {
        for(TestCase testCase:testCaseList){
            testCase.execute();
        }
        return true;
    }
}

package com.yj.testdemo.model;

import lombok.Data;

import java.util.List;

/**
 * 测试步骤
 * @author exyangjun003
 */
@Data
public class TestStep implements TestInterface{
    private List<TestStep> environmentSetList;
    private List<TestStep> preTestList;
    private List<TestStep> testCaseList;
    private List<TestStep> afterTestList;
    private List<TestStep> environmentDestroyList;
    @Override
    public boolean duringTest() {
        return doTestList(testCaseList);
    }
}
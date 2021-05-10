/*
 * this is little boy's personal project,right received by yj6326076@hotmail.com from 2021 to 2021
 */

package com.little.boy.auto.test.common.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * TestCase
 *
 * @author yj632
 * @since 2021-05-10
 */
@Data
@Slf4j
public abstract class TestCase implements TestBaseModel {
    private List<TestStep> preTestSteps;

    private List<TestStep> afterTestSteps;

    private List<TestStep> testSteps;

    @Override
    public void preTest() throws AutoTestException {
        preTestSteps.forEach(TestBaseModel::runTest);
    }

    @Override
    public void afterTest() throws AutoTestException {
        afterTestSteps.forEach(TestBaseModel::runTest);
    }

    @Override
    public void doTest() throws AutoTestException {
        testSteps.forEach(TestBaseModel::runTest);
    }
}

/*
 * this is little boy's personal project,right received by yj6326076@hotmail.com from 2021 to 2021
 */

package com.little.boy.auto.test.common.model;

import lombok.Data;

import java.util.List;

/**
 * TestSuit
 *
 * @author yj632
 * @since 2021-05-10
 */
@Data
public abstract class TestSuit implements TestBaseModel {
    private List<TestCase> preTestCases;
    private List<TestCase> afterTestCases;
    private List<TestCase> testCases;

    @Override
    public void preTest() throws AutoTestException {
        preTestCases.forEach(TestBaseModel::runTest);
    }

    @Override
    public void doTest() throws AutoTestException {
        testCases.forEach(TestBaseModel::runTest);
    }

    @Override
    public void afterTest() throws AutoTestException {
        afterTestCases.forEach(TestBaseModel::runTest);
    }
}

/*
 * this is little boy's personal project,right received by yj6326076@hotmail.com from 2021 to 2021
 */

package com.little.boy.auto.test.common.model;

/**
 * TestBaseModel
 *
 * @author yj632
 * @since 2021-05-10
 */
public interface TestBaseModel {
    /**
     * 预处理方法
     * @throws AutoTestException 自动化测试异常
     */
    public void preTest() throws AutoTestException;

    /**
     * 进行测试方法
     * @throws AutoTestException 自动化测试异常
     */
    public void doTest() throws AutoTestException;

    /**
     * 测试结束方法
     * @throws AutoTestException 自动化测试异常
     */
    public void afterTest() throws AutoTestException;

    /**
     * 执行测试
     * @throws AutoTestException 自动化测试异常
     */
    default void runTest() throws AutoTestException{
        try {
            this.preTest();
            this.doTest();
        } catch (AutoTestException e) {
            // todo doLog;
        } finally {
            afterTest();
        }
    }
}

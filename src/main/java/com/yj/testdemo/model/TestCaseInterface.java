package com.yj.testdemo.model;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author yangjun
 */
public interface TestCaseInterface extends TestInterface {
    /**
     * 获取环境设置内容
     * @return
     * 返回环境设置步骤
     */
    List<? extends TestInterface> getEnvironmentSetList();


    /**
     * 获取测试前步骤内容
     * @return
     * 返回测试前步骤
     */
    List<? extends TestInterface> getPreTestList();

    /**
     * 获取测试后步骤内容
     * @return
     * 返回测试后步骤
     */
    List<? extends TestInterface> getAfterTestList();
    /**
     * 获取环境摧毁步骤内容
     * @return
     * 返回环境摧毁结果
     */
    List<? extends TestInterface> getEnvironmentDestroyList();

    /**
     * 环境配置
     * @return
     * 环境配置结果
     */
    default boolean environmentSet(){
        return doTestList(getEnvironmentSetList());
    }

    /**
     * 测试前准备
     * @return
     * 测试准备结果
     */
    default boolean preTest(){
        return doTestList(getPreTestList());
    }

    /**
     * 执行测试,不可为空
     * @return
     * 执行测试结果
     */
    boolean duringTest();

    /**
     * 测试后
     * @return
     * 测试后处理结果
     */
    default boolean afterTest(){
        return doTestList(getAfterTestList());
    }

    /**
     * 环境销毁
     * @return
     * 环境销毁结果
     */
    default boolean environmentDestroy(){
        return doTestList(getEnvironmentDestroyList());
    }

    /**
     * 用例执行
     * @return
     * 用例执行结果
     */
    @Override
    default boolean execute(){
        if (!environmentSet()){
            return false;
        }
        if (!preTest()){
            return false;
        }
        if (!duringTest()){
            return false;
        }
        if (!afterTest()){
            return false;
        }
        return environmentDestroy();
    }
    /**
     * 执行传入的测试步骤
     * @param testList
     * 执行步骤
     * @return
     * 执行结果
     */
    default boolean doTestList(List<? extends TestInterface> testList) {
        if (CollectionUtils.isEmpty(testList)) {
            return true;
        }
        for(TestInterface testInterface:testList){
            if(!testInterface.execute()){
                return false;
            }
        }
        return true;
    }
}

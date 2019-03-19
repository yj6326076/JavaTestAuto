package com.yj.testdemo.service;

import com.yj.testdemo.vo.ResultVo;

public interface HttpService {
    /**
     * @param test
     * 测试内容
     * @return
     * 测试结果
     */
    ResultVo doHttpTest(String test);
}

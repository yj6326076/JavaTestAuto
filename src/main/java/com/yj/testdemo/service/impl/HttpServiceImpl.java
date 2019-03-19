package com.yj.testdemo.service.impl;

import com.yj.testdemo.model.TestSuit;
import com.yj.testdemo.vo.ResultVo;
import com.yj.testdemo.service.HttpService;
import org.springframework.stereotype.Service;

/**
 * http测试相关内容
 * @author yangjun
 */
@Service
public class HttpServiceImpl implements HttpService {
    @Override
    public ResultVo doHttpTest(String test) {
        new TestSuit().execute();
        return null;
    }
}

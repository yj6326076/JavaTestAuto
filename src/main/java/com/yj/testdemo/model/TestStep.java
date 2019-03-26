package com.yj.testdemo.model;

import lombok.Data;

import java.util.List;

/**
 * 测试步骤
 * @author exyangjun003
 */
@Data
public class TestStep implements TestInterface{

    @Override
    public boolean execute(){
        return true;
    }
}
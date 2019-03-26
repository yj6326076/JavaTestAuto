package com.yj.testdemo.model.environment;

/**
 * 环境类型
 * @author yangjun
 */

public enum EnvironmentType {
    /**
     * 环境类型
     */
    HTTPCLIENT,
    ASYNHTTPCLIENT,
    SELENIUM,
    APPNIUM,
    NET;
    /**
     * 等于
     */
    public boolean equalsName(String name){
        return name().equalsIgnoreCase(name);
    }
}

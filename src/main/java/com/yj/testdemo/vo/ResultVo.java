package com.yj.testdemo.vo;

import lombok.Data;

/**
 * @author exyangjun003
 */
@Data
public class ResultVo<T> {
    private static Integer SUCCESS=0;
    private static Integer PARAM_ERROR=1;
    private Integer stateCode;
    private String message;
    private T result;

    public static <T> ResultVo<T> success(T t){
        return ResultVo.result(ResultVo.SUCCESS,t,null);
    }
    public static <T> ResultVo<T> success(T t,String message){
        return ResultVo.result(ResultVo.SUCCESS,t,message);
    }

    public static <T> ResultVo<T> error(T t){
        return ResultVo.result(ResultVo.PARAM_ERROR,t,null);
    }

    public static <T> ResultVo<T> error(T t,String message){
        return ResultVo.result(ResultVo.PARAM_ERROR,t,message);
    }

    public static <T> ResultVo<T> result(Integer code,T t,String message){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setResult(t);
        resultVo.setStateCode(code);
        resultVo.setMessage(message);
        return resultVo;
    }
}

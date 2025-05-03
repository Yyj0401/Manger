package com.yst.management.common;


import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 成功：无数据
     */
    public static <V> Result<V> success(){
        Result result=new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    /**
     * 成功：有数据
     */
    public static <V> Result<V> success(V data){
        Result<V> result=new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 失败：使用已定义枚举
     */
    public static <V> Result<V> error(){
        Result result=new Result<>();
        result.setCode(ResultCode.FAILURE.getCode());
        result.setMsg(ResultCode.FAILURE.getMsg());
        return result;
    }

    /**
     * 失败：自定义提示信息
     */
    public static <V> Result<V> error(String msg){
        Result result=new Result<>();
        result.setCode(ResultCode.FAILURE.getCode());
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败：自定义提示信息、错误码
     */
    public static <V> Result<V> error(Integer code,String msg){
        Result result=new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}

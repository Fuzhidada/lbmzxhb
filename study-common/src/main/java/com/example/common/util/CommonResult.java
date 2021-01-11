package com.example.common.util;

import lombok.Data;

/**
 * @author fuzhi
 */
@Data
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    private CommonResult(T data) {
        this.code = 200L;
        this.data = data;
    }

    /**
     * 带message的构造方法表明业务出错
     */
    private CommonResult(long code, String message) {
        this.code = code;
        this.message = message;
    }

    private CommonResult(String message) {
        this.code = 500L;
        this.message = message;
    }

    public static CommonResult<Object> success(Object data) {
        return new CommonResult<>(data);
    }

    public static CommonResult<Object> failed(long code, String message) {
        return new CommonResult<>(code, message);
    }

    public static CommonResult failed(String message) {
        return new CommonResult<>(message);
    }
}

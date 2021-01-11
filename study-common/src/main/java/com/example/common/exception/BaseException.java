package com.example.common.exception;


import lombok.Getter;


public class BaseException extends Exception {

    public static final String MESSAGE = "未知异常";


    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }


}

package com.example.exception;


import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

public class BaseException extends Exception {

    @Getter
    private String message="未知异常";


    public BaseException(String message) {
        super(message);
        if(StringUtils.isNotBlank(message)){
            this.message=message;
        }
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }


}

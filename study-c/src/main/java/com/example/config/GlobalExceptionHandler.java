package com.example.config;

import com.example.common.exception.BaseException;
import com.example.common.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public CommonResult catchException(Exception e) {

        log.error(e.fillInStackTrace().getLocalizedMessage());

        if (e instanceof MethodArgumentNotValidException) {
            StringBuilder message = new StringBuilder();

            for (ObjectError error : ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors()) {
                message.append(error.getDefaultMessage()).append("; ");
            }
            return CommonResult.failed(500L, message.toString());
        }
        if (e instanceof BaseException) {
            return CommonResult.failed(500L, e.getMessage());
        }

        return CommonResult.failed(500L, "未知的异常哦哦");
    }

}

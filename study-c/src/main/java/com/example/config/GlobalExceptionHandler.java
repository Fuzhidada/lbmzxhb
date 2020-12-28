package com.example.config;

import com.example.util.CommonResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Iterator;

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
        return CommonResult.failed(500L, e.getMessage());
    }

}

package com.example.constant;

public class BaseEnum {
    private int code;
    private String message;

    BaseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseEnum() {

    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

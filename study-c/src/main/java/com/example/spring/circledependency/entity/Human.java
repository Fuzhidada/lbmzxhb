package com.example.spring.circledependency.entity;

import lombok.Data;

/**
 * @author fuzhi
 */
@Data
public class Human {
    private volatile String name;
    private String sex;
    private int age;
}

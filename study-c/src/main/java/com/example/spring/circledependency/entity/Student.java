package com.example.spring.circledependency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 如果domain中没有重写toString, 且使用了@Data注解, 调用toString时只会打印子类本身的属性值, 如果想要打印父类的属性:
 * 方式一: 重写tostring
 * 方式二: 子类加上@Data和@ToString(callSuper = true)两个注解, 父类也使用注解@Data
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Service
public class Student extends Human {
    @Autowired
    private Teacher Teacher;


/*
    public Student(Teacher teacher) {
        this.teacher = teacher;
    }
*/

}

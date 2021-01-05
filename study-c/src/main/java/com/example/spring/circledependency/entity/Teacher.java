package com.example.spring.circledependency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@EqualsAndHashCode(callSuper = true)
@Data
@Service
//@Scope("prototype")
public class Teacher extends Human {
    @Resource
    private Student student;

    /**
     *     这种循环依赖没有什么解决办法，
     *     因为JVM虚拟机在对类进行实例化的时候，需先实例化构造器的参数，
     *     而由于循环引用这个参数无法提前实例化，故只能抛出错误。
     *  有参构造器产生的循环依赖问题
     *
     * 初始化teacher时，有参构造器去寻找student， student进行初始化，
     * 但是student也依赖了teacher，student不能成功加载，teacher也创建失败
     *
     */
/*    public Teacher(Student student) {
        this.student = student;
    }*/

    /**
     * 这种无参构造器，在方法里循环依赖字段属性 ，
     * 循环依赖问题被spring解决，不会异常
     */
    public Teacher() {
        System.out.println(student);
    }


}

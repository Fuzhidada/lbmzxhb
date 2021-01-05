package com.example.config;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义注解的实现
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = MoomVk.class)

public @interface Moom {

    String message() default "默认回调提示";

    //必要的 groups
    Class<?>[] groups() default {};

    //必要的payload
    Class<? extends Payload>[] payload() default {};

}

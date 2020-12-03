package com.example.vo;

import com.example.config.Moom;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author fuzhi
 */
@Data
public class DefaultQueryVo {

    @Min(value = 1, message = "页码最小为1")
    private Integer page;

    @Max(value = 100, message = "页码最大为100")
    private Integer limit;

    @Moom(message = "mmm测试自定义注解--")
    private Integer dd;

}

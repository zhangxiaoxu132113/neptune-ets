package com.water.neptune.ets.common.dao.plus.annotations;

import java.lang.annotation.*;

/**
 * @author zhangmiaojie
 * @date 2017/11/23
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictDesc {

    String typeName() default "";

    String refField() default "";
}
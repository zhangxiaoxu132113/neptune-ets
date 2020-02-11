package com.water.neptune.ets.common.dao.plus.annotations;

import java.lang.annotation.*;

/**
 * @author zhangmiaojie
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormatMask {
    String type() default "";

    String value() default "";
}
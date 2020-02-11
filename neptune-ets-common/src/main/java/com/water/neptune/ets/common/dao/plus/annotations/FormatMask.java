package com.water.neptune.common.dao.plus.annotations;

import java.lang.annotation.*;

/**
 * Created by zmj on 2017/11/23.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormatMask {
    String type() default "";

    String value() default "";
}
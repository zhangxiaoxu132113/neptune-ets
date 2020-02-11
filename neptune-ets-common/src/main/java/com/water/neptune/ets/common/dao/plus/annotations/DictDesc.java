package com.water.neptune.common.dao.plus.annotations;

import java.lang.annotation.*;

/**
 * Created by zmj on 2017/11/23.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictDesc {
    String typeName() default "";

    String refField() default "";
}
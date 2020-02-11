package com.water.neptune.ets.common.dao.plus.annotations;

import java.lang.annotation.*;

/**
 * @author zhangmiaojie
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NumFormat {
    /**
     * 小数位数
     *
     * @return
     */
    int digit() default 2;

    /**
     * 进位方式
     *
     * @return
     */
    int type() default 0;


}

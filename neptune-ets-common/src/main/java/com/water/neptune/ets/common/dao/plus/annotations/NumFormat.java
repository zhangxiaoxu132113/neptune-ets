package com.water.neptune.common.dao.plus.annotations;

import java.lang.annotation.*;

/**
 * @author 张淼洁
 * @description 描述此类
 * Date: 2018/12/21
 * Time: 16:48
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

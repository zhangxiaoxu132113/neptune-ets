package com.water.neptune.ets.common.dao.plus.mybatis.interceptor;

import com.water.neptune.ets.common.dao.plus.annotations.NumFormat;
import com.water.neptune.ets.common.dao.plus.mybatis.model.PageResult;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import static com.hankcs.hanlp.corpus.tag.Nature.o;


/**
 * @author 张淼洁
 * @description 数字格式化-拦截器
 * Date: 2018/12/21
 * Time: 16:46
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class NumFormatInterceptor implements Interceptor {
    private static final String GET = "get";
    private static final String SET = "set";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if (result instanceof PageResult) {
            PageResult pageResult = (PageResult) result;
            List list = pageResult.getResult();
            for (Object o : list) {
                Class<?> clazz = o.getClass();
                formatNum(o, clazz);
            }
        } else if (result instanceof List) {
            List list = (List) result;
            for (Object o : list) {
                Class<?> clazz = o.getClass();
                formatNum(o, clazz);
            }
        } else {
            Class<?> clazz = result.getClass();
            formatNum(o, clazz);
        }
        return result;
    }

    private void formatNum(Object object, Class<?> clazz) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);

            if (declaredField.isAnnotationPresent(NumFormat.class)) {
                NumFormat annotation = declaredField.getAnnotation(NumFormat.class);
                int digit = annotation.digit();

                Object value = formatNum(object, declaredField, digit);
                clazz.getMethod(getMethodName(SET, declaredField.getName()), value.getClass()).invoke(object, value);
            }
        }
    }

    private Object formatNum(Object object, Field declaredField, int digit) throws IllegalAccessException {
        BigDecimal decimal;
        Object declaredFieldObject = declaredField.get(object);
        if (declaredFieldObject instanceof BigDecimal) {
            decimal = (BigDecimal) declaredFieldObject;
            decimal = decimal.setScale(digit, BigDecimal.ROUND_HALF_DOWN);
            return decimal;

        } else if (declaredFieldObject instanceof Double) {
            decimal = BigDecimal.valueOf((Double) declaredFieldObject);
            decimal = decimal.setScale(digit, BigDecimal.ROUND_HALF_DOWN);
            return decimal.doubleValue();

        } else if (declaredFieldObject instanceof Float) {
            decimal = BigDecimal.valueOf((Float) declaredFieldObject);
            decimal = decimal.setScale(digit, BigDecimal.ROUND_HALF_DOWN);
            return decimal.floatValue();
        }

        throw new RuntimeException("不支持格式化的数字类型！");
    }

    private String getMethodName(String type, String fieldName) {
        return type + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

package com.water.neptune.common.dao.plus.mybatis.interceptor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

import com.water.neptune.common.dao.plus.annotations.DictDesc;
import com.water.neptune.common.dao.plus.ditc.DictOperater;
import com.water.neptune.common.dao.plus.mybatis.model.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


/**
 * @author 张淼洁
 * @description 字典值转换-拦截器
 * Date: 2018/12/21
 * Time: 16:46
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class DictInterceptor implements Interceptor {
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
                cvs(o, clazz);
            }
        } else if (result instanceof List) {
            List list = (List) result;
            for (Object o : list) {
                Class<?> clazz = o.getClass();
                cvs(o, clazz);
            }
        } else {
            Class<?> aClass = result.getClass();
            cvs(result, aClass);
        }
        return result;
    }

    private void cvs(Object o, Class<?> clazz) throws Exception {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(DictDesc.class)) {
                DictDesc annotation = declaredField.getAnnotation(DictDesc.class);
                String type = annotation.typeName();
                if (StringUtils.isEmpty(type) || StringUtils.isEmpty(annotation.refField())) {
                    break;
                }
                String code = String.valueOf(clazz.getMethod(getMethodName(GET, annotation.refField())).invoke(o));
                clazz.getMethod(getMethodName(SET, declaredField.getName()), String.class).invoke(o, DictOperater.getDictionaryDesc(type, code));
            }
        }
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


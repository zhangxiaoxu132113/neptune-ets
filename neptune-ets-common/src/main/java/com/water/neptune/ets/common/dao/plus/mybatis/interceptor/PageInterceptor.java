package com.water.neptune.ets.common.dao.plus.mybatis.interceptor;

import com.water.neptune.ets.common.dao.plus.mybatis.PageSqlFactory;
import com.water.neptune.ets.common.dao.plus.mybatis.executor.PageExecutor;
import com.water.neptune.ets.common.dao.plus.mybatis.model.PageParam;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author 张淼洁
 * @description 分页-拦截器 Date: 2018/12/21 Time: 16:46
 */
@Intercepts({
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class}),
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    /**
     * 需要进行分页操作的字符串正则表达式
     */
    private String pattern = "^.*page.*$";

    /**
     * 数据库方言：目前只支持mysql、oracle、sqlServer；默认mysql
     */
    private String dialect = "mysql";

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            return handleStatementHandler(invocation);
        }
        return invocation.proceed();
    }

    /**
     * @param invocation
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private Object handleStatementHandler(Invocation invocation)
            throws InvocationTargetException, IllegalAccessException {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());

        BoundSql boundSql = statementHandler.getBoundSql();
        Object paramObject = boundSql.getParameterObject();
        PageParam pageParam = PageParam.getPageParam(paramObject);

        if (pageParam == null) {
            return invocation.proceed();
        }

        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                    new DefaultReflectorFactory());
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                    new DefaultReflectorFactory());
        }

        String sql = boundSql.getSql();
        // 重写sql
        String pageSql = PageSqlFactory.getPageSqlByDialect(this.dialect, sql, pageParam);
        metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
        // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        if (Executor.class.isAssignableFrom(o.getClass())) {
            PageExecutor executor = new PageExecutor((Executor) o, pattern);
            return Plugin.wrap(executor, this);
        } else if (o instanceof StatementHandler) {
            return Plugin.wrap(o, this);
        }
        return o;
    }

    @Override
    public void setProperties(Properties properties) {
    }

}

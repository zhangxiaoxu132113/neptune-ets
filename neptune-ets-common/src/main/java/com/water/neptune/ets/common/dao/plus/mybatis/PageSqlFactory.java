package com.water.neptune.ets.common.dao.plus.mybatis;


import com.water.neptune.ets.common.dao.plus.mybatis.model.PageParam;

/**
 * @author ZhangMiaojie
 * Created On 2017/11/23.
 */
public class PageSqlFactory {
    /**
     * 根据数据库方言获取分页查询语句（目前只支持mysql、oracle、sqlServer；默认mysql）
     *
     * @param dialect
     * @param originalSql
     * @param pageParam
     * @return
     * @author Taocong
     * @date 2017年9月28日 下午4:12:57
     */
    public static String getPageSqlByDialect(String dialect, String originalSql, PageParam pageParam) {
        String pageSql = null;
        int offSet = (pageParam.getDefaultPage() - 1) * pageParam.getDefaultPageSize();
        if (null == dialect || "mysql".equals(dialect)) {
            pageSql = originalSql + " LIMIT " + offSet + "," + pageParam.getDefaultPageSize();
        } else if ("oracle".equals(dialect)) {
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("select * from ( select tmp_page.*, rownum row_id from ( ");
            sqlBuilder.append(originalSql);
            sqlBuilder.append(" ) tmp_page where rownum <= ");
            sqlBuilder.append(pageParam.getDefaultPageSize());
            sqlBuilder.append(" ) where row_id > ");
            sqlBuilder.append(offSet);
            pageSql = sqlBuilder.toString();
        } else if ("sqlServer".equals(dialect)) {
            // 找到sql中的排序
            // (由于indexof不能使用正则，所以先使用replaceAll对sql中的排序语法规范化;(?i)标识忽略大小写;\\s*表示空格出现一次或多次)
            int orderStartIndex = originalSql.replaceAll("(?i)ORDER\\s+BY", "ORDER BY").lastIndexOf("ORDER BY");
            String orderStr = "ORDER BY n";
            // 有排序，且是最外层的排序
            if (orderStartIndex != -1 && originalSql.lastIndexOf(")") < orderStartIndex) {
                orderStr = originalSql.substring(orderStartIndex);
            }
            pageSql = originalSql.replaceFirst("(?i)select", "select * from (select row_number() over(" + orderStr
                    + ") as rownumber,* from ( select top " + (offSet + pageParam.getDefaultPageSize()) + " n=0,");
            pageSql += ")t )tt where rownumber> " + offSet;
        }

        return pageSql;
    }
}

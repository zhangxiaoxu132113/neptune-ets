package com.water.neptune.common.dao.plus.mybatis.model;

import java.util.Map;

import lombok.Data;

/**
 * @author 张淼洁
 * @description 描述此类 Date: 2018/12/19 Time: 13:52
 */
@Data
public class PageParam {
	private Integer defaultPage;
	/**
	 * 默认每页显示条数
	 */
	private Integer defaultPageSize;
	/**
	 * 是否启用分页功能
	 */
	private boolean defaultUseFlag;
	/**
	 * 是否检测当前页码的合法性（大于最大页码或小于最小页码都不合法）
	 */
	private boolean defaultCheckFlag;

	public PageParam() {
	}

	public PageParam(Integer page, Integer pageSize) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			// 默认每页显示10条数据
			pageSize = 50;
		}
		this.defaultPage = page;
		this.defaultPageSize = pageSize;
	}

	/**
	 * 获取分页的参数 参数可以通过map，@param注解进行参数传递。或者请求pojo继承自PageParam 将PageParam中的分页数据放进去
	 *
	 * @param paramerObject
	 * @return
	 */
	public static PageParam getPageParam(Object paramerObject) {
		if (paramerObject == null) {
			return null;
		}
		PageParam pageParam = null;
		// 通过map和@param注解将PageParam参数传递进来，pojo继承自PageParam不推荐使用
		// 这里从参数中提取出传递进来的pojo继承自PageParam
		// 首先处理传递进来的是map对象和通过注解方式传值的情况，从中提取出PageParam,循环获取map中的键值对，取出PageParam对象
		if (paramerObject instanceof Map) {
			Map<String, Object> params = (Map<String, Object>) paramerObject;
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (entry.getValue() instanceof PageParam) {
					return (PageParam) entry.getValue();
				}
			}
		} else if (paramerObject instanceof PageParam) {
			// 继承方式 pojo继承自PageParam 只取出我们希望得到的分页参数
			pageParam = (PageParam) paramerObject;
		}
		return pageParam;
	}
}

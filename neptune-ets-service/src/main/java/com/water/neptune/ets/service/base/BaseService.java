package com.water.neptune.common.service.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.Property;
import com.water.neptune.common.dao.plus.mybatis.model.PageParam;
import com.water.neptune.common.dao.plus.mybatis.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.water.neptune.common.core.exception.ServiceException;
import com.water.neptune.common.core.redis.RedisCacheManager;
import com.water.neptune.common.enums.CodeStatusEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Zhang Miaojie
 * @version v1.0
 * @date 2019/6/26
 */
public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

	@Autowired
	protected RedisCacheManager redisCacheManager;

	/**
	 * 删除数据
	 *
	 * @param id
	 * @param queryWrapper
	 * @throws ServiceException
	 */
	public void removeDataById(Long id, Wrapper<T> queryWrapper) throws ServiceException {
		if (id == null) {
			throw new ServiceException(CodeStatusEnum.AOP_INVALID_PARAMETER);
		}
		T videoInfoPO = this.getOne(queryWrapper);
		if (videoInfoPO == null) {
			throw new ServiceException("数据不存在!");
		}
		this.remove(queryWrapper);
	}

    private Map<String, Object> getQueryCondition(int page, int pageSize, Object condition) {
        Map<String, Object> queryParams = new HashMap<>();
        PageParam pageParam = new PageParam(page, pageSize);
        queryParams.put("pageParam", pageParam);
        queryParams.put("condition", condition);
        return queryParams;
    }

    /**
     * 分页
     *
     * @param page
     * @param pageSize
     * @param condition
     * @param function
     * @return
     */
    public <R> PageResult<R> pageByList(int page, int pageSize, Object condition,
                                        Function<Map<String, Object>, PageResult<R>> function) {
        Map<String, Object> queryCondition = getQueryCondition(page, pageSize, condition);
        return function.apply(queryCondition);
    }

    public <R> PageResult<R> getAll(Function<Map<String, Object>, PageResult<R>> function) {
    	return pageByList(1, Integer.MAX_VALUE, null, function);
	}

    public Wrapper<T> getQueryOneCondition(Property<T, ?> column, Object val){
        return new LambdaQueryWrapper<T>().eq(column, val);
    }
}

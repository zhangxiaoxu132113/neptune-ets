//package com.water.neptune.ets.service.base;
//
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.water.neptune.common.core.ICachedDefine;
//import com.water.neptune.common.core.ITimeConstant;
//
//import java.util.function.Function;
//
///**
// * @author Zhang Miaojie
// * @version v1.0
// * @date 2019/6/26
// */
//public class BaseGenericCacheService<M extends BaseMapper<T>, T> extends BaseService<M, T>
//		implements ICachedDefine, ITimeConstant {
//
//	public T getInCache(String key, Class<T> tClass, Function<Void, T> function) {
//		T t = redisCacheManager.get(key, tClass);
//		if (t == null) {
//			t = function.apply(null);
//			redisCacheManager.set(key, t, ONE_DAY_SECONDS);
//		}
//		return t;
//	}
//
//}

package com.wanda.creditapp.common.cache;

import org.apache.log4j.Logger;

import redis.clients.jedis.ShardedJedis;

import com.wanda.creditapp.common.domain.BaseDomain;
import com.wanda.creditapp.common.redis.ShardedJedisUtil;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.common.util.StringUtil;

public class RedisCache {
	private static final Logger logger = Logger.getLogger(RedisCache.class);

	/**
	 * 存入字符串value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String setStr(String key, String value) {
		ShardedJedis j = ShardedJedisUtil.getShardedJedis();
		String result = j.set(key, value);
		j.close();
		return result;
	}
	
	/**
	 * 设置key-value,并制定超时时间
	 * @param key
	 * @param value
	 * @param seconds//超时时间（单位秒）
	 * @return
	 */
	public String setStrWithExpiry(String key, String value,int seconds){
		ShardedJedis j = ShardedJedisUtil.getShardedJedis();
		String result = j.setex(key, seconds, value);
		j.close();
		return result;
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @return
	 */
	public String getStr(String key) {
		ShardedJedis j = ShardedJedisUtil.getShardedJedis();
		String result = j.get(key);
		j.close();
		return result;
	}

	/**
	 * obj转成json存入redis,并设置超时时间
	 * 
	 * @param key
	 * @param obj
	 * @param seconds  超时时间
	 * @return
	 */
	public String setObjWithExpiry(String key, Object obj,int seconds) {
		String jsonStr = JsonUtil.buildNormalBinder().toJson(obj);
		String result = setStrWithExpiry(key, jsonStr, seconds);
		return result;
	}
	
	/**
	 * obj转成json存入redis
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public String setObj(String key, Object obj) {
		String jsonStr = JsonUtil.buildNormalBinder().toJson(obj);
		String result = setStr(key, jsonStr);
		return result;
	}

	public <T> T getObj(String key, Class<T> clazz) {
		ShardedJedis j = ShardedJedisUtil.getShardedJedis();
		String resultJson = j.get(key);
		j.close();
		if (StringUtil.isBlank(resultJson)) {
			logger.info("RedisCache.getObj-resultJson为空！");
			return null;
		}
		T t = JsonUtil.buildNormalBinder().getJsonToObject(resultJson, clazz);
		return t;
	}
	
	/**
	 * obj转成json存入redis
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public Long delete(String key) {
		ShardedJedis j = ShardedJedisUtil.getShardedJedis();
		Long result = j.del(key);
		j.close();
		return result;
	}

	public static void main(String[] args) {
		RedisCache rc = new RedisCache();
	//	System.out.println(rc.delete("accessToken84b3af16-c580-4b20-8579-8b003bf35546"));
		System.out.println(rc.getStr("refreshToken0a61585d-d829-4142-a407-194c24cf9f6b"));
		
//		BaseDomain b = new BaseDomain();
//		b.setId(1);
//		b.setCreateUser("sssss");
//		rc.setObj("aaa", b);
//
//		BaseDomain c = rc.getObj("aaa", BaseDomain.class);
//		System.out.println(c.getId() + c.getCreateUser());
//		System.out.println(rc.delete("aaa"));
		
	}
}

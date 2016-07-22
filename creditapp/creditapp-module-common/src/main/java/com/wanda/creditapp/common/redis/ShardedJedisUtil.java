package com.wanda.creditapp.common.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisUtil {
	private  static volatile  ShardedJedisPool shardedJedisPool = null;// 切片连接池

	private ShardedJedisUtil() {
	}

	
	public static ShardedJedis getShardedJedis(){
		if(null==shardedJedisPool){
			synchronized(ShardedJedisUtil.class){
				if(null==shardedJedisPool){
					initialShardedPool();
				}
			}
		}
		return shardedJedisPool.getResource();
	}
	
	/**
	 * 初始化切片池
	 */
	private static void initialShardedPool() {
		// 池基本配置
		String ports = "6379";
		String ips = "127.0.0.1";
		String names = "master";
		// slave链接
		JedisPoolConfig config = getConfig();
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		String[] ipsstr = ips.split(";");
		String[] portsstr = ports.split(";");
		String[] namesstr = names.split(";");
		for (int i = 0; i < ipsstr.length; i++) {
			shards.add(new JedisShardInfo(ipsstr[i], Integer.valueOf(portsstr[i]), 5000, namesstr[i]));
		}
		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}
	
	private static JedisPoolConfig getConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(10);
		config.setMaxTotal(50);
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		return config;
	}

}

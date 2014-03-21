/*
 * JedisPoolTest.java
 */
package com.yuer.TestRedis;

import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis Pool 操作
 */
public class JedisMasterPoolTest {

	private JedisPool jedisPool;

	private JedisPoolConfig _initPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 控制一个pool最多有多少个状态为idle的jedis实例
		jedisPoolConfig.setMaxIdle(1000);
		// 最大能够保持空闲状态的对象数
		jedisPoolConfig.setMaxIdle(300);
		// 超时时间
		jedisPoolConfig.setMaxWaitMillis(1000);
		// 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
		jedisPoolConfig.setTestOnBorrow(true);
		// 在还会给pool时，是否提前进行validate操作
		jedisPoolConfig.setTestOnReturn(true);
		return jedisPoolConfig;
	}

	/**
	 * 初始化jedis连接池 <br>
	 * ------------------------------<br>
	 */
	@Before
	public void before() {
		JedisPoolConfig jedisPoolConfig = _initPoolConfig();
		// 属性文件读取参数信息
		String host = "192.168.16.231";
		int port = 6379;
		int timeout = 1000;
		String password = "llt660";
		// 构造连接池
		jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
	}

	@Test
	public void testSet() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.select(3);
			long start = System.nanoTime();
			int total = 50000;
			for (int i = 0; i < total; i++) {
				String key = UUID.randomUUID().toString();
				jedis.set(key, i + "_" + key);
			}
			long count = (System.nanoTime() - start);
			System.out.println("testSet total cast time = " + count + " per:" + count / total * 1.0);
		} catch (Exception e) {
			// 销毁对象
			jedisPool.returnBrokenResource(jedis);
			Assert.fail(e.getMessage());
		} finally {
			// 还会到连接池
			jedisPool.returnResource(jedis);
		}
	}

	@Test
	public void testDelSet() {
		Jedis jedis = null;
		try {
			// 从池中获取一个jedis实例
			jedis = jedisPool.getResource();
			jedis.select(3);
			long start = System.nanoTime();
			Set<String> ret = jedis.keys("*");
			for (String str : ret) {
				jedis.del(str);
			}
			long count = (System.nanoTime() - start);
			System.out.println("testDelSet total cast time = " + count + "  per:" + count / ret.size() * 1.0);
		} catch (Exception e) {
			// 销毁对象
			jedisPool.returnBrokenResource(jedis);
			Assert.fail(e.getMessage());
		} finally {
			// 还会到连接池
			jedisPool.returnResource(jedis);
		}
	}
}

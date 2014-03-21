package com.yuer.TestRedis;

import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * jedis 简单使用
 * 
 */
public class JedisSimpleTest {

	private Jedis jedis;

	/**
	 * 初始化连接 <br>
	 */
	@Before
	public void beforeClass() {
		jedis = new Jedis("192.168.16.231");
		jedis.auth("llt660");
		jedis.select(3);
	}

	@Test
	public void testFlush() {
		long start = System.nanoTime();
		jedis.flushDB();
		System.out.println("testFlush total cast time = " + (System.nanoTime() - start));
	}

	/**
	 * set 新增 <br>
	 * ------------------------------<br>
	 */
	@Test
	public void testSet() {
		long start = System.nanoTime();
		int total = 50000;
		for (int i = 0; i < total; i++) {
			String key = UUID.randomUUID().toString();
			jedis.set(key, i + "_" + key + "_" + key);
		}
		long count = (System.nanoTime() - start);
		System.out.println("testSet total cast time = " + count + " per:" + count / total * 1.0);
	}

	@Test
	public void testDelSet() {
		long start = System.nanoTime();
		Set<String> ret = jedis.keys("*");
		for (String str : ret) {
			jedis.del(str);
		}
		long count = (System.nanoTime() - start);
		System.out.println("testDelSet total cast time = " + count + "  per:" + count / ret.size() * 1.0);
	}

}

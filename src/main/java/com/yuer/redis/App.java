package com.yuer.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		// 池基本配置
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxIdle(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		JedisPool jedisPool = new JedisPool(config, "192.168.16.231", 6379, 1000, "llt660");

		Jedis jedis = jedisPool.getResource();

		jedis.select(10);
		User u = new User("yue1", 1, "code1");
		User u2 = new User("yue2", 2, "code2");
		User u3 = new User("yue3", 3, "code3");
		User u4 = new User("yue3", 3, "code3");

		String str = JSON.toJSONString(u);
		String str2 = JSON.toJSONString(u2);
		String str3 = JSON.toJSONString(u3);
		String str4 = JSON.toJSONString(u4);

		jedis.sadd("sname" + "", str);
		jedis.sadd("sname" + "", str2);
		jedis.sadd("sname" + "", str3);
		jedis.sadd("sname" + "", str4);
	}
}

class User {
	private String name;
	private int id;
	private String code;

	public User(String name, int id, String code) {
		super();
		this.name = name;
		this.id = id;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

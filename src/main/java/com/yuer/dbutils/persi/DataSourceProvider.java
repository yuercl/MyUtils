package com.yuer.dbutils.persi;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.yuer.dbutils.application.Config;

/**
 * MySQL连接池数据源
 */
public class DataSourceProvider {
	// 单例锁
	private static final ReentrantLock lock = new ReentrantLock();
	// 单例
	private static DataSource dataSource;

	/**
	 * 初始化数据源
	 * 
	 * @param dataSource
	 */
	public static void initDataSource(Config config) {
		lock.lock();
		dataSource = _setupDataSource(config);
		lock.unlock();
	}

	/**
	 * 创建数据源，除了数据库外，都使用硬编码默认参数；
	 * 
	 * @param connectURI
	 *            数据库
	 * @return
	 */
	public static void initDataSource(String connectURI) {
		initDataSource(connectURI, "root", "password", "com.mysql.jdbc.Driver", 5, 100, 30, 10000);
	}

	/**
	 * 指定所有参数连接数据源,初始化了 DS ，指定 所有参数
	 * 
	 * @param connectURI
	 *            数据库
	 * @param username
	 *            用户名
	 * @param pswd
	 *            密码
	 * @param driverClass
	 *            数据库连接驱动名
	 * @param initialSize
	 *            初始连接池连接个数
	 * @param maxActive
	 *            最大激活连接数
	 * @param maxIdle
	 *            最大闲置连接数
	 * @param maxWait
	 *            获得连接的最大等待毫秒数
	 * @return
	 */
	public static void initDataSource(String connectURI, String username, String pswd, String driverClass, int initialSize, int maxActive, int maxIdle,
			int maxWait) {
		lock.lock();
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUsername(username);
		ds.setPassword(pswd);
		ds.setUrl(connectURI);
		ds.setInitialSize(initialSize); // 初始的连接数
		ds.setMaxActive(maxActive);
		ds.setMaxIdle(maxIdle);
		ds.setMaxWait(maxWait);
		dataSource = ds;
		lock.unlock();
	}

	/**
	 * 获得一个MySQL数据源
	 */
	public static DataSource getDataSource() {
		if (dataSource == null) {
			throw new RuntimeException("DataSource is not init! call 'DataSourceProvider.initDataSource' first!");
		}
		return dataSource;
	}

	/**
	 * 设置数据源
	 * 
	 * @param config
	 */
	private static DataSource _setupDataSource(Config config) {
		try {
			String driver = config.get("driver");
			String url = config.get("url");
			String user = config.get("user");
			String password = config.get("password");
			int initialSize = config.getInt("initialSize");
			int maxActive = config.getInt("maxActive");
			int maxIdle = config.getInt("maxIdle");
			long timeBetweenEvictionRunsMillis = config.getLong("timeBetweenEvictionRunsMillis");
			long minEvictableIdleTimeMillis = config.getLong("minEvictableIdleTimeMillis");

			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setUrl(url);
			dataSource.setDriverClassName(driver);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			dataSource.setInitialSize(initialSize);
			dataSource.setMaxActive(maxActive);
			dataSource.setMaxIdle(maxIdle);
			dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

			dataSource.setValidationQuery("select 1 as status");
			dataSource.setTestWhileIdle(true);
			dataSource.setLogAbandoned(true);

			return dataSource;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 获得数据源连接状态 */
	public static Map<String, Integer> getDataSourceStats() throws SQLException {
		BasicDataSource bds = (BasicDataSource) dataSource;
		Map<String, Integer> map = new HashMap<String, Integer>(2);
		map.put("active_number", bds.getNumActive());
		map.put("idle_number", bds.getNumIdle());
		map.put("initial_size", bds.getInitialSize());
		return map;
	}

	/** 关闭数据源 */
	protected static void shutdownDataSource() throws SQLException {
		BasicDataSource basicDataSource = (BasicDataSource) dataSource;
		basicDataSource.close();
	}

}
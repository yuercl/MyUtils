package com.yuer.dbutils.persi;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.yuer.dbutils.application.Config;

/**
 * MySQL连接池数据源
 * @author <a href="mailto:joe.dengtao@gmail.com">DengTao</a>
 * @version 1.0
 * @since 1.0
 */
public class DataSourceProvider {

	// 单例锁
	private static final Object lock = new Object();
	// 单例
	private static DataSource dataSource;
	
	/**
	 * 初始化数据源
	 * @param dataSource
	 */
	public static void initDataSource(Config config) {
		synchronized (lock) {
			dataSource = setupDataSource(config);
		}
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

	private static DataSource setupDataSource(Config config) {
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
}
package com.yuer.dbutils.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuer.dbutils.dao.UserDao;
import com.yuer.dbutils.entity.User;
import com.yuer.dbutils.exception.ConfigInvalidException;
import com.yuer.dbutils.persi.DataSourceProvider;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		try {
			Config datasource = Config.parseFromXml("/config/datasource.xml");
			logger.info("[加载配置][成功加载数据库相关配置]");
			DataSourceProvider.initDataSource(datasource);
			logger.info("[初始数据源][初始化成功]");
			UserDao userDao = new UserDao();
			List<User> userList = userDao.getAll();
			for (User user : userList) {
				logger.info(user.toString());
			}
		} catch (ConfigInvalidException e) {
			e.printStackTrace();
		}

	}
}

package com.yuer.beanutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.ResultSetDynaClass;

public class BeanUtilsExample3 {
	public static void main(String args[]) throws Exception {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("select id,title,time from guestbook2 order by id desc");
		ResultSet rs = ps.executeQuery();

		ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);// 重点，二次封装，对连接对象有依赖
		Iterator<?> itr = rsdc.iterator();
		while (itr.hasNext()) {
			DynaBean bean = (DynaBean) itr.next();
			System.out.print(bean.get("id") + "\t");
			System.out.print(bean.get("title") + "\t");
			System.out.println(bean.get("time"));
		}
		conn.close();
	}

	private static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/guestbook";
		String username = "root";
		String password = "hicc";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
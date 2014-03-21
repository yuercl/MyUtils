package com.yuer.beanutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;

public class BeanUtilsExample4 {
	public static void main(String args[]) throws Exception {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("select id,title,time from guestbook2 order by id desc");
		ResultSet rs = ps.executeQuery();

		RowSetDynaClass rsdc = new RowSetDynaClass(rs);
		// 重点，与ResultSetDynaClass的区别
		conn.close();// 重点，关闭连接后仍能读取
		Iterator<?> itr = rsdc.getRows().iterator();
		while (itr.hasNext()) {
			DynaBean bean = (DynaBean) itr.next();
			System.out.print(bean.get("id") + "\t");
			System.out.print(bean.get("title") + "\t");
			System.out.println(bean.get("time"));
		}
	}

	private static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/guestbook";
		String username = "root";
		String password = "";
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
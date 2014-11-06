package com.yuer.postgresql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PostgresqlSample {
	
	public static void main(String[] args) throws IOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String url = "jdbc:postgresql://localhost:5432/chat";
		String user = "postgres";
		String password = "yuer";
		ArrayList<String> paths = new ArrayList<String>();
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("select * from weather");
			while (rs.next()) {
				paths.add(rs.getString(1));
				System.out.println(rs.getString(1));
			}

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
	}
}
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnect {
	public static Connection conn;
	public sqlConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/12306?serverTimezone=UTC&useSSL=true"; 
			conn = DriverManager.getConnection(url,"root","yj6326076");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clean() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

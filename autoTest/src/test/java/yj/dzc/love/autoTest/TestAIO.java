package yj.dzc.love.autoTest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestAIO {

	public static Connection conn;
	public static void main(String[] args) {
		prepare();
		File fs = new File("image/");
		for (File f : fs.listFiles()) {
			String md5 = f.getName().replaceAll("\\.jpg", "");
			save(md5);
		}
		clean();
	}
	
	public static void prepare() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/12306?serverTimezone=UTC"; 
			conn = DriverManager.getConnection(url,"root","yj6326076");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void clean() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void save(String md5) {
		try {
			PreparedStatement pre = conn.prepareStatement("INSERT INTO aio (AIOmd5) VALUES (?)");
			pre.setString(1, md5);
			pre.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

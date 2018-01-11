package yj.dzc.love.autoTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class SaveImageToMysql {
	public static Connection conn;
	public SaveImageToMysql() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/12306?serverTimezone=UTC"; 
			conn = DriverManager.getConnection(url,"root","yj6326076");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveImage(File f) {
		try {
			saveImage(new FileInputStream(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void saveImage(InputStream is) {
		try {
			byte[] array = IOUtils.toByteArray(is);
			String md5 = DigestUtils.md5Hex(array);
			if(duplicate_checking(md5)) {
				PreparedStatement pre = conn.prepareStatement("INSERT INTO image_copy1 (image_md5,image_stream) VALUES (?,?)");
				pre.setString(1,md5);
				pre.setBlob(2, is);
				pre.execute();
				pre.close();
			}
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
	
	public static boolean duplicate_checking(String md5) {
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT COUNT(1) FROM image_copy1 WHERE image_md5 = ?");
			pre.setString(1, md5);
			pre.execute();
			ResultSet rs = pre.getResultSet();
			rs.next();
			int count = rs.getInt(1);
			if(count == 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

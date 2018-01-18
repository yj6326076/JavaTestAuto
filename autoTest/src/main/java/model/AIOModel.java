package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AIOModel extends sqlConnect {
	protected int id;
	protected String MD5;
	protected int is_category;
	protected String verification_resutl;

	public AIOModel(String md5) {
		super();
		this.MD5 = md5;
		this.init_by_md5();
	}

	private final void init_by_md5() {
		if (duplicate_checking())
			this.insert();
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT * FROM aio WHERE AIOmd5 = ?");
			pre.setString(1, this.MD5);
			pre.execute();
			ResultSet rs = pre.getResultSet();
			while (rs.next()) {
				this.id = rs.getInt("id");
				this.is_category = rs.getInt("is_category");
				this.verification_resutl = rs.getString("verification_resutl");
			}
			pre.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private final void insert() {
		try {
			PreparedStatement pre = conn.prepareStatement("INSERT INTO aio (AIOmd5) VALUES (?)");
			pre.setString(1, this.MD5);
			pre.execute();
			pre.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final boolean duplicate_checking() {
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT COUNT(1) FROM aio WHERE AIOmd5 = ?");
			pre.setString(1, this.MD5);
			pre.execute();
			ResultSet rs = pre.getResultSet();
			rs.next();
			int count = rs.getInt(1);
			pre.close();
			if (count == 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public final boolean id_category() {
		boolean flag = false;
		if (is_category == 1)
			flag = true;
		return flag;
	}
	
	public final boolean change_status() throws SQLException {
		boolean flag = false;
		PreparedStatement pre = conn.prepareStatement("UPDATE aio a set a.is_category = 1 WHERE a.id = ?");
		pre.setLong(1, this.id);
		int i = pre.executeUpdate();
		if(i>0)
			flag = true;
		pre.close();
		return flag;
	}
	
}
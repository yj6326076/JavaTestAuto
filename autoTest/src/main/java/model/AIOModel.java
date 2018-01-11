package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AIOModel extends sqlConnect {
	public AIOModel() {
		super();
	}
	
	public void save(String md5) {
		if (duplicate_checking(md5)) {
			try {
				PreparedStatement pre = conn.prepareStatement("INSERT INTO aio (AIOmd5) VALUES (?)");
				pre.setString(1, md5);
				pre.execute();
				pre.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean duplicate_checking(String md5) {
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT COUNT(1) FROM aio WHERE AIOmd5 = ?");
			pre.setString(1, md5);
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
}
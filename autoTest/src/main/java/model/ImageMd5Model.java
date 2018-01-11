package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class ImageMd5Model extends sqlConnect {
	private long num;

	public ImageMd5Model() {
		super();
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT COUNT(1) FROM image_copy1");
			pre.execute();
			ResultSet rs = pre.getResultSet();
			rs.next();
			this.num = rs.getInt(1);
			pre.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveImage(File f) {
		try {
			saveImage(new FileInputStream(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveImage(InputStream is) {
		try {
			byte[] array = IOUtils.toByteArray(is);
			String md5 = DigestUtils.md5Hex(array);
			if (duplicate_checking(md5)) {
				PreparedStatement pre = conn
						.prepareStatement("INSERT INTO image_copy1 (image_md5,image_stream) VALUES (?,?)");
				pre.setString(1, md5);
				pre.setBlob(2, is);
				pre.execute();
				pre.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean duplicate_checking(String md5) {
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT COUNT(1) FROM image_copy1 WHERE image_md5 = ?");
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

	public boolean save_category(String md5, String category) {
		if (!duplicate_checking(md5)) {
			try {
				PreparedStatement pre = conn
						.prepareStatement("UPDATE image_copy1 SET category = ? WHERE image_md5 = ? ");
				pre.setString(1, category);
				pre.setString(2, md5);
				pre.execute();
				pre.close();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<MD5Model> query(long i, int size) {
		ArrayList<MD5Model> lis = new ArrayList<MD5Model>();
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT * FROM image_copy1 LIMIT ?, ?");
			pre.setLong(1, i);
			pre.setLong(2, size);
			pre.execute();
			ResultSet set = pre.getResultSet();
			while (set.next()) {
				MD5Model md5model = new MD5Model(set.getInt(1), set.getString(2), set.getBinaryStream(3),
						set.getString(4));
				lis.add(md5model);
			}
			pre.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lis;
	}

	public long get_num() {
		return this.num;
	}
}

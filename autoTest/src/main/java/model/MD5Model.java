package model;

import java.io.InputStream;

public class MD5Model {
	private int id;
	private String md5;
	private InputStream IS;
	private String category;
	public MD5Model(int i,String md,InputStream input,String cate) {
		this.id = i;
		this.md5 = md;
		this.IS = input;
		this.category = cate;
	}
	
	public int get_id() {
		return this.id;
	}
	
	public String get_md5() {
		return this.md5;
	}
	
	public InputStream get_input() {
		return this.IS;
	}
	
	public String get_cate() {
		return this.category;
	}
	
	public void set_cate(String cate) {
		this.category = cate;
	}
}

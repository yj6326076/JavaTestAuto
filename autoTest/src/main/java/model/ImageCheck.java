package model;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

public class ImageCheck extends AIOModel {
	private InputStream in;

	public ImageCheck(String md5) {
		super(md5);
	}

	public void set_input(InputStream input) {
		this.in = input;
		if (StringUtils.isNotEmpty(this.verification_resutl)) {
			recheck();
		}
	}

	private void recheck() {
	}
}

package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;

public class CheckImageModel {
	private InputStream input;

	public CheckImageModel(InputStream in) {
		this.input = in;
	}

	public String get_info() {
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
		ImageReader reader = null;
		while (readers.hasNext()) {
			reader = readers.next();
			if (reader.canReadRaster()) {
				break;
			}
		}
		reader.setInput(input);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(0, 0, 293, 28);
		param.setSourceRegion(rect);
		try {
			BufferedImage bi = reader.read(0, param);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}

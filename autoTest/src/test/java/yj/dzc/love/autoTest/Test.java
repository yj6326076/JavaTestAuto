package yj.dzc.love.autoTest;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.codec.digest.DigestUtils;

public class Test {

	public static void main(String[] args) {
		File fs = new File("image/");
		for (File f : fs.listFiles()) {
			for (int i = 0; i < 8; i++) {
				cat(f.getName(), i);
			}
			System.out.println(f.getName());
		}
	}

	public static void cat(String fileName, int num) {
		try {
			File f = new File("image/" + fileName);
			// Find a suitable ImageReader
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
			ImageReader reader = null;
			while (readers.hasNext()) {
				reader = readers.next();
				if (reader.canReadRaster()) {
					break;
				}
			}

			// Stream the image file (the original CMYK image)
			ImageInputStream input = ImageIO.createImageInputStream(f);
			reader.setInput(input);

			ImageReadParam param = reader.getDefaultReadParam();
			// Rectangle rect = new Rectangle(5, 41, 67, 67);
			Rectangle rect = null;
			switch (num) {
			case 0:
				rect = new Rectangle(5, 41, 67, 67);
				break;
			case 1:
				rect = new Rectangle(77, 41, 67, 67);
				break;
			case 2:
				rect = new Rectangle(149, 41, 67, 67);
				break;
			case 3:
				rect = new Rectangle(221, 41, 67, 67);
				break;
			case 4:
				rect = new Rectangle(5, 113, 67, 67);
				break;
			case 5:
				rect = new Rectangle(77, 113, 67, 67);
				break;
			case 6:
				rect = new Rectangle(149, 113, 67, 67);
				break;
			case 7:
				rect = new Rectangle(221, 113, 67, 67);
				break;
			default:
				rect = new Rectangle(5, 41, 67, 67);
				break;
			}
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);

			ByteArrayOutputStream bt = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", bt);
			byte[] ba = bt.toByteArray();
			String md5 = DigestUtils.md5Hex(ba);
			File fof = new File("image1/" + md5 + ".jpg");
			if (!fof.exists()) {
				FileOutputStream fo = new FileOutputStream(fof);
				ImageIO.write(bi, "jpg", fo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
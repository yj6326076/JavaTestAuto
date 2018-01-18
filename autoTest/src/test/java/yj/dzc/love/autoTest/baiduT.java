package yj.dzc.love.autoTest;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class baiduT {
	// TODO 自动生成的方法存根
	//设置APPID/AK/SK
    public static final String APP_ID = "10683404";
    public static final String API_KEY = "DpoS17i16Li4G4GeX4VaxYNm";
    public static final String SECRET_KEY = "QaOZNQ6szKyGwv0nc33Z4xz3U39Xic0h";

	public static void main(String[] args) {
	 // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

		File fs = new File("E:\\资料\\12306\\image\\");
		int i = 0;
		for (File f : fs.listFiles()) {
			byte[] image = get_jpg(f);
			if(null == image)
				continue;
	        // 调用接口
	        JSONObject res = client.basicGeneral(image, new HashMap<String, String>());
	        System.out.println(res.toString(2));
	        if(i >= 500)
	        	break;
	        i ++;
	        
		}
        
	}
	
	public static byte[] get_jpg(File f) {
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
		ImageReader reader = null;
		while (readers.hasNext()) {
			reader = readers.next();
			if (reader.canReadRaster()) {
				break;
			}
		}

		// Stream the image file (the original CMYK image)
		ImageInputStream input;
		try {
			input = ImageIO.createImageInputStream(f);
			reader.setInput(input);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(120, 0, 120, 28);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ByteArrayOutputStream bt = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", bt);
			byte[] ba = bt.toByteArray();
			return ba;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}

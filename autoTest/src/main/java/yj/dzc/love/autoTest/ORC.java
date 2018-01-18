package yj.dzc.love.autoTest;

import java.awt.Rectangle;
import java.io.File;
import java.util.Date;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ORC {

	public static void main(String[] args) {
		Date dt = new Date();
		File fs = new File("E:\\资料\\12306\\image\\");
		for (File f : fs.listFiles()) {
			get_info(f);
			System.out.println(f.getName());
			System.out.println("***********");
		}
		System.out.println(new Date().getTime() - dt.getTime());
	}

	public static void get_info(File imageFile) {
		ITesseract instance = new Tesseract();
		// 默认是英文（识别字母和数字），如果要识别中文(数字 + 中文），需要制定语言包
		instance.setLanguage("chi_sim");
		try {
			String result = instance.doOCR(imageFile, new Rectangle(120, 0, 120, 28));
			System.out.println(result);
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}
	}
}

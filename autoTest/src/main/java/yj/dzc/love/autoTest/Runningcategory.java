package yj.dzc.love.autoTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import model.BaiduModel;
import model.ImageMd5Model;
import model.MD5Model;

public class Runningcategory {

	public static void main(String[] args) {
		// ImageMd5Model m5m = new ImageMd5Model();
		// long num = m5m.get_num();
		// for(long i = 0;i < num;i = i + 1000) {
		// ArrayList<MD5Model> rs = m5m.query(i, 1000);
		// for(MD5Model m:rs) {
		// System.out.println(m.get_id());
		// }
		// }
		// m5m.clean();
		File f = new File("000fd3aefa5817c79cb9b5da39d81fa6.jpg");
		try {
			BaiduModel bd = new BaiduModel();
			FileInputStream ins = new FileInputStream(f);
			String html = bd.upload(ins, "000fd3aefa5817c79cb9b5da39d81fa6");
			System.out.println(html);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try {
		// Document doc = Jsoup.parse(new File("baidu.html"),"utf8");
		// doc.select("");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}

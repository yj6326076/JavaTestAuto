package model;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BaiduModel extends HttpModel {

	public String uploadurl = "http://image.baidu.com/pcdutu/a_upload?fr=html5&target=pcSearchImage&needJson=true";
	public String categoryurl = "http://image.baidu.com/pcdutu?queryImageUrl=";
	public BaiduModel() throws Exception {
		super();
	}
	
	
	public String upload(InputStream is,String md5) {
		String pathurl = null;
		try {
			HttpPost post = new HttpPost(uploadurl);
			InputStreamBody bin = new InputStreamBody(is,md5 + ",jpg");
			HttpEntity enti = MultipartEntityBuilder.create()
					.setBoundary("------WebKitFormBoundaryI3kVkYhuctP4UckZ")
					.addPart("file", bin)
					.build();
			post.setEntity(enti);
			CloseableHttpResponse response = httpclient.execute(post);
			HttpEntity eni = response.getEntity();

			Gson gs = new Gson();
			HashMap<String, String> rs = gs.fromJson(EntityUtils.toString(eni),new TypeToken<HashMap<String, String>>(){}.getType());
			pathurl = rs.get("url");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return get_category(pathurl);
	}
	
	@SuppressWarnings("resource")
	private String get_category(String url) {
		WebClient wc = new WebClient();
		wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		wc.getOptions().setCssEnabled(false); // 禁用css支持
		wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
		wc.getOptions().setTimeout(10000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
		try {
			HtmlPage page = wc.getPage(categoryurl + url);
			String pageXml = page.asXml();
			Document doc = Jsoup.parse(pageXml);
			Element pv = doc.select(".guess-info-word-link").get(0);
			return pv.text();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

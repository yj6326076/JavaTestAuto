package yj.dzc.love.autoTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class mp3 {

	public static void main(String[] args) {

		HttpClient httpClient = new DefaultHttpClient();

		HttpPost request;
		try {
			request = new HttpPost("https://www.sogou.com/web?query=" + URLEncoder.encode("凉凉", "UTF-8"));

			HttpResponse response;
			response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			String jsonString = EntityUtils.toString(entity);
			Document doc = Jsoup.parse(jsonString);
			Elements tbody = doc.select("div._music_list");
			Element body = tbody.get(0);
			Elements trs = body.select("tr");
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				Elements ElNames = tr.select("._music_songname");
				for (int j = 0; j < ElNames.size(); j++) {
					Element ElName = ElNames.get(j);
					System.out.println("name:" + ElName.text() + "download:" + ElName.attr("href"));
					System.out.println("**********************");
				}
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}

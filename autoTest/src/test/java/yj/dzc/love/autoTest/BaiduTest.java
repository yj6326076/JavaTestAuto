package yj.dzc.love.autoTest;

import java.io.File;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BaiduTest {
	public static CloseableHttpClient httpclient;

	// public static final String APP_ID = "10650265";
	// public static final String API_KEY = "GOuK9z8clU9zKZGFsWs8vIOU";
	// public static final String SECRET_KEY = "SObhFGbj34dHNYBxRT21OAnV5BMBlHxc";
	public static void main(String[] args) {
		// // 初始化一个AipImageClassifyClient
		// AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
		//
		// // 可选：设置网络连接参数
		// client.setConnectionTimeoutInMillis(2000);
		// client.setSocketTimeoutInMillis(60000);
		//
		// // 调用接口
		// String path = "000a921c5d426efc52b8b9e5be81d5e6.jpg";
		// JSONObject res = client.objectDetect(path, new HashMap<String, String>());
		// System.out.println(res.toString(2));

		getImagePath("");
	}

	public static void restartClient() throws Exception {
		if (httpclient != null) {
			httpclient.close();
		}
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
	
	public static String getImagePath(String respon) {
		String test = "{\"errno\":0,\"url\":\"http://g.hiphotos.baidu.com/image/%70%69%63/item/ac4bd11373f082027c941ad240fbfbedaa641bdc.jpg\",\"querySign\":\"3421061202,4277027924\",\"simid\":\"0,0\"}";
		
		Gson gs = new Gson();
		HashMap<String, String> rs = gs.fromJson(test,new TypeToken<HashMap<String, String>>(){}.getType());
		System.out.println(rs.get("url"));
		return null;
	}
	
	public static String uploadImage(String path) {
		String pathurl = null;
		try {
			restartClient();
			HttpPost post = new HttpPost(
					"http://image.baidu.com/pcdutu/a_upload?fr=html5&target=pcSearchImage&needJson=true");
			FileBody bin = new FileBody(new File(path));
			HttpEntity enti = MultipartEntityBuilder.create()
					.setBoundary("------WebKitFormBoundaryI3kVkYhuctP4UckZ")
					.addPart("file", bin)
					.build();
			post.setEntity(enti);
			CloseableHttpResponse response = httpclient.execute(post);
			HttpEntity eni = response.getEntity();
			pathurl = getImagePath(EntityUtils.toString(eni));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathurl;
	}
}

package yj.dzc.love.autoTest;

import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class HttpTest {

	public static CloseableHttpClient httpclient;
	public static void main(String[] args) {
		try {
			restartClient();
			FileEntity enti = new FileEntity(new File("image1/fffda0a693b58c5c3a13116833fac1a5.jpg"));
			HttpPost post = new HttpPost("https://api.cognitive.azure.cn/vision/v1.0/tag");
			post.setHeader("Content-Type", "application/octet-stream");
			post.setHeader("Ocp-Apim-Subscription-Key", "c7c77b878ca042399baa312bca99bb08");
			post.setEntity(enti);
			CloseableHttpResponse response = httpclient.execute(post);
			HttpEntity eni = response.getEntity();
			System.out.println(EntityUtils.toString(eni));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}

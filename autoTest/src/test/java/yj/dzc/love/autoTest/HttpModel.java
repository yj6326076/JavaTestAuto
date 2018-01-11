/**
 * 
 */
package yj.dzc.love.autoTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

/**
 * @author yj
 *
 */
public class HttpModel {

	public static CloseableHttpClient httpclient;

	/**
	 * @param args
	 * @throws IOException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */

	public static void main(String[] args) throws Exception {
		restartClient();
		for (int i = 0; i < 10000; i++) {
			get();
			if (i % 5 == 0) {
				restartClient();
			}
		}
		httpclient.close();
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

	/**
	 * 进行get取验证图的流
	 * 
	 * @throws Exception
	 */
	public static void get() throws Exception {
		HttpGet httpget = new HttpGet(
				"https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&"
						+ Math.random());
		httpget.setHeader("Referer", "https://kyfw.12306.cn/otn/login/init");
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");

		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				byte[] array = EntityUtils.toByteArray(entity);
				String md5 = DigestUtils.md5Hex(array);
				File file = new File("image/" + md5 + ".jpg");
				if ("266cada4a821414cb2a369e3700c40dd".equals(md5)) {
					System.out.println("wrong ");
				} else if (file.exists()) {
					System.out.println(md5);
				} else {
					System.out.println("save:" + md5);
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(array);
					fos.close();
				}
			}
		} finally {
			response.close();
		}
	}

}

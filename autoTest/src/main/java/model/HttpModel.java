package model;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

public class HttpModel {
	private static CloseableHttpClient httpclient = null;
	private static HttpModel httpmodel;
	private HttpModel() throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		httpclient = HttpClients.custom().setSSLSocketFactory(sslsf)
				.setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(2000).setSocketTimeout(2000)
						.setConnectTimeout(2000).build())
				.setRetryHandler(new DefaultHttpRequestRetryHandler(5, false)).build();
	}
	
	public HttpModel getHttpClient() {
		if(httpmodel == null) {
			try {
				httpmodel = new HttpModel();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return httpmodel;
	}
	
}

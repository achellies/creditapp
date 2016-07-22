package com.wanda.creditapp.remote.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class RequestHelper {
	private static final Logger logger = Logger.getLogger(RequestHelper.class);
	// private static final int timeout = 10000;
	// 征信系统的慢接口超时为2分钟，这里设置为5分钟
	private static final int timeout = 300000;
	private static final String CODE_FORMAT = "UTF-8";
	private static String codeFormat = CODE_FORMAT;
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static volatile CloseableHttpClient client;
	// 设置请求超时时间�?DEFAULT_CHARSET。可以后写到配置文件
	private final static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();

	/**
	 * 初始化httpClient, SSLContext信任密钥
	 * 
	 * @return CloseableHttpClient
	 */
	private static CloseableHttpClient getClient() {
		if (null == client) {
			synchronized (RequestHelper.class) {
				if (client == null) {
					SSLConnectionSocketFactory sslCSF = null;
					try {
						// 信任密钥
						SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
						sslCSF = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
					client = HttpClients.custom().setSSLSocketFactory(sslCSF).build();
				}
			}
		}
		return client;
	}

	/**
	 * 发起请求(HttpPost/HttpGet)，关闭资源
	 */
	private static String execute(HttpUriRequest request) throws ParseException, IOException {
		String responseStr = null;
		CloseableHttpResponse httpResponse = null;

		httpResponse = getClient().execute(request);
		responseStr = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
		httpResponse.close();
		return responseStr;
	}

	/**
	 * 生成请求的url
	 * 
	 * @param url
	 *            不带参数的url字符串
	 * @param params
	 *            请求参数
	 * @return 请求的uri对象
	 */
	private static URI generateURL(String url, Map<String, String> params) {
		URI uri = null;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (null != params) {
				for (Entry<String, String> entry : params.entrySet()) {
					uriBuilder.addParameter(entry.getKey(), entry.getValue());
				}
			}
			uri = uriBuilder.build();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
		}
		return uri;
	}

	/**
	 * 发送get请求
	 */
	public static String doGet(String url, Map<String, String> params) throws ParseException, IOException {
		URI uri = generateURL(url, params);
		HttpGet get = new HttpGet(uri);
		get.setConfig(requestConfig);
		logger.info("HTTP Request url: " + uri);
		long start = new Date().getTime();
		String res = execute(get);
		logger.info("HTTP Response context: \n" + res);
		logger.info("HTTP costs：" + (new Date().getTime() - start));
		return res;
	}

	/**
	 * 发送POST请求
	 */
	private static volatile CloseableHttpClient httpClient = null;

	public static String doPost(String url, Map<String, String> params, boolean https) throws NoSuchAlgorithmException, KeyManagementException {
		List<org.apache.http.NameValuePair> nvps = new ArrayList<org.apache.http.NameValuePair>();
		if (params != null) {
			Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				nvps.add(new BasicNameValuePair(key, value));
			}
		}
		StringBuffer result = new StringBuffer();
		try {
			if (httpClient == null) {
				synchronized (RequestHelper.class) {
					if (https) {
						SSLContext ctx = SSLContext.getInstance("TLS");
						X509TrustManager tm = new X509TrustManager() {
							public X509Certificate[] getAcceptedIssuers() {
								return null;
							}

							public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
							}

							public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
							}
						};
						ctx.init(null, new TrustManager[] { tm }, null);
						/*
						 * SSLSocketFactory ssf = new SSLSocketFactory(ctx,
						 * SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
						 * SchemeRegistry registry = new SchemeRegistry();
						 * registry.register(new Scheme("https", 443, ssf));
						 * ThreadSafeClientConnManager mgr = new
						 * ThreadSafeClientConnManager(registry); httpClient =
						 * new
						 * org.apache.http.impl.client.DefaultHttpClient(mgr);
						 */

						// 指定信任密钥存储对象和连接套接字工厂
						LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
						RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory> create();
						registryBuilder.register("https", sslSF);

						Registry<ConnectionSocketFactory> registry = registryBuilder.build();
						// 设置连接管理器
						PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
						// 构建客户端
						httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
					} else {
						httpClient = HttpClientBuilder.create().build();
					}
					/*
					 * HttpParams httpParams = httpClient.getParams(); //
					 * 设定12秒超时，届时会弹出Exception
					 * HttpConnectionParams.setConnectionTimeout(httpParams,
					 * httpTimeOut);
					 * HttpConnectionParams.setSoTimeout(httpParams,
					 * httpTimeOut);
					 */
				}
			}
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, codeFormat));
			long start = new Date().getTime();
			CloseableHttpResponse response = httpClient.execute(httpPost);
			logger.info("HTTP Request url: " + url);
			logger.info("HTTP costs：" + (new Date().getTime() - start));
			url = URLDecoder.decode(url, "UTF-8");
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			in.close();
			response.close();
		} catch (UnsupportedEncodingException e) {
			logger.error("call remote service occur an UnsupportedEncodingException:", e);
		} catch (ClientProtocolException e) {
			logger.error("call remote service occur an ClientProtocolException:", e);
		} catch (IOException e) {
			logger.error("call remote service occur an IOException:", e);
		}
		return result.toString();
	}

}

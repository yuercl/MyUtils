/**
 * project name: myNews
 * created at 2013-3-6 - 下午2:16:29
 * author:yuer
 * email:yuerguang.cl@gmail.com
 */
package com.yuer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	private static final int CONNECTION_POOL_SIZE = 10;
	private static final int TIMEOUT_SECONDS = 20;

	HttpClient httpclient;
	HttpResponse response;

	public HttpUtils() {
		PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
		cm.setMaxTotal(CONNECTION_POOL_SIZE);
		httpclient = new DefaultHttpClient(cm);
		httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		// set timeout
		HttpParams httpParams = httpclient.getParams();

		// 设置http头
		List<Header> headers = new ArrayList<Header>();
		BasicHeader header = new BasicHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		headers.add(header);
		httpclient.getParams().setParameter("http.default-headers", headers);

		httpclient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_SECONDS * 1000);

	}

	public List<Cookie> getCookies() {
		return ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
	}

	public Map<String, String> getMapCookies() {
		Map<String, String> map = new HashMap<String, String>();
		List<Cookie> cookies = ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
		for (Cookie c : cookies) {
			map.put(c.getName(), c.getValue());
		}
		return map;
	}

	public String getCookie(String key) {
		for (Cookie c : ((AbstractHttpClient) httpclient).getCookieStore().getCookies()) {
			if (c.getName().equals("key"))
				return c.getValue();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public String get(String url) throws Exception {
		return this.get(url, HTTP.UTF_8);
	}

	@SuppressWarnings("deprecation")
	public String get(String url, String encode) throws Exception {
		logger.info("[正在抓取地址为：" + url + "]");
		HttpGet httpget = new HttpGet(url);
		response = httpclient.execute(httpget);
		HttpEntity httpEntity = response.getEntity();
		String html = null;
		if (httpEntity != null) {
			html = EntityUtils.toString(httpEntity, encode);
			httpEntity.consumeContent();
		}
		return html;
	}

	@SuppressWarnings("deprecation")
	public String post(String url, List<NameValuePair> nvps, String encode) throws Exception {
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new UrlEncodedFormEntity(nvps, encode));
		response = httpclient.execute(httppost);
		HttpEntity httpEntity = response.getEntity();
		String html = null;
		if (httpEntity != null) {
			html = EntityUtils.toString(httpEntity, encode);
			httpEntity.consumeContent();
		}
		return html;
	}

	@SuppressWarnings("deprecation")
	public String post(String url, List<NameValuePair> nvps) throws Exception {
		return this.post(url, nvps, HTTP.UTF_8);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public String post(String url, Map map) throws IOException {
		List<NameValuePair> nvps = new ArrayList();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			NameValuePair nvp = new BasicNameValuePair(key, value);
			nvps.add(nvp);
		}
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		response = httpclient.execute(httppost);
		HttpEntity httpEntity = response.getEntity();
		String html = null;
		if (httpEntity != null) {
			html = EntityUtils.toString(httpEntity);
			httpEntity.consumeContent();
		}
		return html;
	}

	@SuppressWarnings("deprecation")
	public String post(String url, String str) throws Exception {
		httpclient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
		HttpPost httppost = new HttpPost(url);// "http://web-proxy.qq.com/conn_s"
		StringEntity reqEntity = new StringEntity(str);
		httppost.setEntity(reqEntity);
		response = httpclient.execute(httppost);
		HttpEntity httpEntity = response.getEntity();
		String html = null;
		if (httpEntity != null) {
			html = EntityUtils.toString(httpEntity);
			httpEntity.consumeContent();
		}
		return html;
	}

	@SuppressWarnings("deprecation")
	public void getImg(String url, String path) throws IOException {
		HttpGet httpget = new HttpGet(url);
		response = httpclient.execute(httpget);
		HttpEntity httpEntity = response.getEntity();
		byte[] b = EntityUtils.toByteArray(httpEntity);
		File storeFile = new File(path);
		FileOutputStream output = new FileOutputStream(storeFile);
		output.write(b);
		output.close();
		if (httpEntity != null) {
			httpEntity.consumeContent();
		}
	}

	public void close() throws IOException {
		httpclient.getConnectionManager().shutdown();
	}

}

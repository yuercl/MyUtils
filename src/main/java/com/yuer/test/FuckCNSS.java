package com.yuer.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://freshman.cnssuestc.org/problem/30.php 
 * project Test FuckCNSS.java <br />
 * create time 2013年10月16日 下午2:52:58 <br /> <br />
 * @author yuer <br />
 */
public class FuckCNSS {
	private static Logger logger = LoggerFactory.getLogger(FuckCNSS.class);

	public static void main(String args[]) {
		String str = "";
		String key = "";
		String value = "";
		try {
			str = Jsoup.connect("http://192.168.16.231:9999/start?token=me").get().getElementsByTag("body").get(0).html();
			logger.info(str);
			while (true) {
				String params[] = str.split(" ");
				key = params[1];
				value = params[3];
				if (str.contains("Post")) {
					str = Jsoup.connect("http://192.168.16.231:9999/" + value).data("token", key).post().getElementsByTag("body").get(0).html();
					logger.info(str);
				} else {
					str = Jsoup.connect("http://192.168.16.231:9999/" +value  + "?token=" + key).get().getElementsByTag("body").get(0).html();
					logger.info(str);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

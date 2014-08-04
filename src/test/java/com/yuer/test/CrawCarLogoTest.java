package com.yuer.test;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuer.utils.HttpUtils;

public class CrawCarLogoTest {
	private Logger logger = LoggerFactory.getLogger(CrawCarLogoTest.class);

	@Test
	public void start() {
		try {
			Document doc = Jsoup.connect("http://www.car-logos.org/").get();
			Elements elements = doc.getElementsByClass("ngg-gallery-thumbnail-box");
			if (elements != null) {
				for (int i = 0; i < elements.size(); i++) {
					Elements imgs = elements.get(i).getElementsByTag("a");
					if (imgs != null) {
						logger.info(imgs.get(1).toString());
						String detailUrl = imgs.get(1).attr("href");
						Document detailDoc = Jsoup.connect("http://www.car-logos.org" + detailUrl).get();
						Element tr = detailDoc.getElementById("content").getElementsByTag("table").get(0).getElementsByTag("tr").get(0);
						Element img = tr.getElementsByClass("size-full").get(0);
						String logo = img.attr("src");
						logger.info("[" + logo + "]");
						String[] names = logo.split("/");
						String name = names[names.length - 1];
						HttpUtils httpUtils = new HttpUtils();
						String path = "C:\\cars\\" + name;
						httpUtils.getImg(logo, path);
						logger.info("[save to =" + path + "]");
					}
				}
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

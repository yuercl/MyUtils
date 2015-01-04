package com.yuer.basic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;


/**
 * 导出虾米音乐到酷狗播放列表类型，可以导入到网易云音乐
 */
public class XiamiMusic {

	private Map<String, String> cookiesMap = new HashMap<String, String>();
	private String UA = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36";
	private String cookies = "your cookie here";

	@Before
	public void init() {
		String[] items = cookies.split(";");
		for (String tmp : items) {
			if (tmp.contains("CNZZDATA")) {
				continue;
			}
			String[] ts = tmp.split("=");
			cookiesMap.put(ts[0].trim(), ts[1].trim());
		}

	}

	@Test
	public void testGetList() throws IOException {
		String url = "http://www.xiami.com/space/lib-song/u/26551797/page/%s?spm=a1z1s.6928797.1561534521.429.euoJ77";
		System.out.println(cookiesMap);
		int page = 1;
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"windows-1252\"?><List ListName=\"做个测试提交\">");
		while (true) {
			Document doc = Jsoup.connect(String.format(url, page++)).cookies(cookiesMap).userAgent(UA).get();
			Elements trs = doc.getElementsByClass("track_list").get(0).getElementsByTag("tr");
			if (trs.size() < 1) {
				break;
			}
			for (int i = 0; i < trs.size(); i++) {
				String name = trs.get(i).getElementsByTag("a").get(0).attr("title");
				Elements artistNames = trs.get(i).getElementsByClass("artist_name");
				String artistName = "";
				for (int j = 0; j < artistNames.size(); j++) {
					artistName += artistNames.get(j).html() + "/";
				}
				artistName = artistName.substring(0, artistName.length() - 1);
				artistName = artistName.replace("&egrave;", "è");// e hack , & cannt accur in xml 
				String file = "<File><FileName>" + artistName.trim() + " - " + name.trim() + ".mp3</FileName></File>";
				xml.append(file);
				System.out.println(file);
			}
		}
		xml.append("</List>");
		System.out.println("\n\n\n");
		System.out.println("put this line into file which you want to import into neteast music. \"kugou.kgl\"");
		System.out.println(xml.toString());
	}
}

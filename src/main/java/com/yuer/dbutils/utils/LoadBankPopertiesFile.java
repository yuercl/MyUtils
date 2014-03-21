package com.yuer.dbutils.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoadBankPopertiesFile {
	private static Logger logger = LoggerFactory.getLogger(LoadBankPopertiesFile.class);

	public static Map<String, String> loadFile(String path) {
		Map<String, String> retMap = new HashMap<String, String>();
		if (null == path || "".equals(path.trim())) {
			path = "/config/banks.properties";
			logger.info("[加载配置][配置为空，采用默认配置]");
		}
		path = path.trim();
		InputStream is = LoadBankPopertiesFile.class.getResourceAsStream(path);

		Properties prop = new Properties();
		try {
			prop.load(is);// 从输入流中读取属性列表
		} catch (IOException e) {
			logger.error("load poperties file faile." + e);
			return null;
		} catch (Exception e) {
			logger.error("load poperties file faile." + e);
			return null;
		}
		// 返回Properties中包含的key-value的Set视图
		Set<Entry<Object, Object>> set = prop.entrySet();
		// 返回在此Set中的元素上进行迭代的迭代器
		Iterator<Map.Entry<Object, Object>> it = set.iterator();
		String key = null, value = null;
		// 循环取出key-value
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			key = String.valueOf(entry.getKey());
			value = String.valueOf(entry.getValue());
			key = key == null ? key : key.trim().toUpperCase();
			value = value == null ? value : value.trim().toUpperCase();
			// 将key-value放入BANK_NAME map中,这里故意将value和key反放
			retMap.put(value, key);
		}
		return retMap;
	}
}
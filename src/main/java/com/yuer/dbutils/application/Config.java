package com.yuer.dbutils.application;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import com.yuer.dbutils.exception.ConfigInvalidException;
import com.yuer.dbutils.utils.StringUtils;
import com.yuer.dbutils.utils.XMLUtils;

/**
 * 通用配置文件，用于读取：
 * <pre>
 * 	&lt;?xml version='2.0' encoding='GBK'?&gt;
 * 	&lt;config>
 * 		&lt;param name="key1" value="value1" /&gt;
 * 		&lt;param name="key2"&gt;value2&lt/param&gt;
 * 	&lt;/config&gt;
 * </pre>
 * @author <a href="mailto:joe.dengtao@gmail.com">DengTao</a>
 * @version 2.0
 * @since 2.0
 */
public class Config {
	
	/** value map */
	private Map<String, String> map = new ConcurrentHashMap<String, String>();

	/**
	 * 解析通用配置文件得到{@link Config}对象.
	 * @param xmlPath
	 * @return 通用配置文件得到{@link Config}对象.
	 * @throws DocumentException
	 */
	public static Config parseFromXml(String xmlPath) throws ConfigInvalidException {
		try {
			Config config = new Config();
			Document document = XMLUtils.parse(Config.class.getResource(xmlPath));
//			Document document = XMLUtils.parse(xmlPath);
			@SuppressWarnings("unchecked")
			List<Node> numList = document.selectNodes("//config/param");
			for (Node numNode : numList) {
				String name = numNode.valueOf("@name");
				String value = numNode.valueOf("@value");
				if (StringUtils.isBlank(value)) value = numNode.getText();
				config.put(name, value);
			}
			return config;
		} catch (Exception e) {
			throw new ConfigInvalidException("解析配置文件:" + xmlPath + "出现异常:" + e);
		}
	}

	/**
	 * 获取key对应的value值
	 * @param key
	 * @return key对应的value值,不存在返回null
	 */
	public String get(String key) {
		synchronized (map) {
			return map.get(key);
		}
	}

	/**
	 * 获取key对应的value值
	 * @param key
	 * @return key对应的value值,不存在返回false
	 */
	public boolean getBoolean(String key) {
		try {
			return Boolean.parseBoolean(get(key));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取key对应的value值
	 * @param key
	 * @return key对应的value值,不存在返回0
	 */
	public float getFloat(String key) {
		try {
			return Float.parseFloat(get(key));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取key对应的value值
	 * @param key
	 * @return key对应的value值,不存在返回0
	 */
	public int getInt(String key) {
		try {
			return Integer.parseInt(get(key));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取key对应的value值
	 * @param key
	 * @return key对应的value值,不存在返回0
	 */
	public long getLong(String key) {
		try {
			return Long.parseLong(get(key));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 添加key-value映射到配置中.
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		synchronized (map) {
			map.put(key, value);
		}
	}

	/**
	 * 添加key-value映射到配置中.
	 * @param key
	 * @param value
	 */
	public void put(String key, boolean value) {
		put(key, value + "");
	}

	/**
	 * 添加key-value映射到配置中.
	 * @param key
	 * @param value
	 */
	public void put(String key, int value) {
		put(key, value + "");
	}

	/**
	 * 添加key-value映射到配置中.
	 * @param key
	 * @param value
	 */
	public void put(String key, float value) {
		put(key, value + "");
	}

	/**
	 * 添加key-value映射到配置中.
	 * @param key
	 * @param value
	 */
	public void put(String key, long value) {
		put(key, value + "");
	}
	
}